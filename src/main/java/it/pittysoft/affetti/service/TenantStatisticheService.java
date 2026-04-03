package it.pittysoft.affetti.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import it.pittysoft.affetti.dto.ChartDataDto;
import it.pittysoft.affetti.dto.ChartGroupDataDto;
import it.pittysoft.affetti.dto.ChartSeriesItemDto;
import it.pittysoft.affetti.dto.TenantStatisticheDto;

@Service
public class TenantStatisticheService {

	@PersistenceContext
	private EntityManager em;

	// Subquery comune: seleziona i posti di un tenant tramite la catena aree->cimiteri
	private static final String POSTI_BY_TENANT =
		"SELECT p.id FROM posti p JOIN aree a ON p.fk_area = a.id JOIN cimiteri cim ON a.fk_cimitero = cim.id WHERE cim.fk_tenant = :tid";

	public TenantStatisticheDto getStatistiche(Long tenantId, List<Long> cimiteroIds) {
		TenantStatisticheDto dto = new TenantStatisticheDto();
		String cimFilter = buildCimiteroFilter(cimiteroIds);
		String postiSub = POSTI_BY_TENANT + cimFilter;

		// ===== KPI =====
		dto.setTotaleDefunti(countScalar(
			"SELECT COUNT(*) FROM defunti WHERE fk_posto IN (" + postiSub + ")", tenantId));
		dto.setPostiTotali(countScalar(
			"SELECT COUNT(*) FROM posti p JOIN aree a ON p.fk_area = a.id JOIN cimiteri cim ON a.fk_cimitero = cim.id WHERE cim.fk_tenant = :tid" + cimFilter, tenantId));
		dto.setPostiLiberi(countScalar(
			"SELECT COUNT(*) FROM posti p JOIN aree a ON p.fk_area = a.id JOIN cimiteri cim ON a.fk_cimitero = cim.id WHERE cim.fk_tenant = :tid AND p.stato = 'LIBERO'" + cimFilter, tenantId));
		dto.setDomandeAperte(countScalar(
			"SELECT COUNT(*) FROM domande WHERE stato = 'APERTA' AND fk_posto IN (" + postiSub + ")", tenantId));
		dto.setContrattiInScadenza(countScalarWithDate(
			"SELECT COUNT(*) FROM contratti c JOIN domande dom ON c.fk_domanda = dom.id WHERE dom.fk_posto IN (" + postiSub + ") AND c.data_scadenza <= :scadenza",
			tenantId, LocalDate.now().plusMonths(6).toString()));

		// ===== DISTRIBUZIONI =====
		dto.setPostiPerStato(groupBy(
			"SELECT p.stato, COUNT(*) FROM posti p JOIN aree a ON p.fk_area = a.id JOIN cimiteri cim ON a.fk_cimitero = cim.id WHERE cim.fk_tenant = :tid" + cimFilter + " GROUP BY p.stato", tenantId));
		dto.setPostiPerTipoSepoltura(groupBy(
			"SELECT ts.codice, COUNT(*) FROM posti p JOIN aree a ON p.fk_area = a.id JOIN cimiteri cim ON a.fk_cimitero = cim.id JOIN tipi_sepoltura ts ON p.fk_tipo_sepoltura = ts.id WHERE cim.fk_tenant = :tid" + cimFilter + " GROUP BY ts.codice", tenantId));
		dto.setDomandePerStato(groupBy(
			"SELECT dom.stato, COUNT(*) FROM domande dom WHERE dom.fk_posto IN (" + postiSub + ") GROUP BY dom.stato", tenantId));
		dto.setSepolturePerTipoOperazione(groupBy(
			"SELECT s.tipo_operazione, COUNT(*) FROM sepolture s WHERE s.fk_posto IN (" + postiSub + ") GROUP BY s.tipo_operazione", tenantId));

		// ===== TREND TEMPORALI =====
		dto.setDecessiPerMese(buildTimeSeries(
			"SELECT d.data_decesso FROM defunti d WHERE d.fk_posto IN (" + postiSub + ") AND d.data_decesso IS NOT NULL",
			"Decessi", tenantId));
		dto.setDomandePerMese(buildTimeSeries(
			"SELECT dom.data_protocollo FROM domande dom WHERE dom.fk_posto IN (" + postiSub + ") AND dom.data_protocollo IS NOT NULL",
			"Domande", tenantId));
		dto.setContrattiPerMese(buildTimeSeries(
			"SELECT c.data_inizio FROM contratti c JOIN domande dom ON c.fk_domanda = dom.id WHERE dom.fk_posto IN (" + postiSub + ") AND c.data_inizio IS NOT NULL",
			"Contratti", tenantId));

		// ===== CONFRONTI =====
		dto.setPostiPerStatoPerCimitero(buildGroupedByCimitero(
			"SELECT cim.nome, p.stato, COUNT(*) FROM posti p JOIN aree a ON p.fk_area = a.id JOIN cimiteri cim ON a.fk_cimitero = cim.id WHERE cim.fk_tenant = :tid" + cimFilter + " GROUP BY cim.nome, p.stato ORDER BY cim.nome",
			tenantId));
		dto.setCapienzaVsOccupazione(buildCapienzaVsOccupazione(tenantId, cimiteroIds));

		return dto;
	}

	private String buildCimiteroFilter(List<Long> cimiteroIds) {
		if (cimiteroIds == null || cimiteroIds.isEmpty()) return "";
		return " AND cim.id IN (" + cimiteroIds.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
	}

	private long countScalar(String sql, Long tenantId) {
		Object result = em.createNativeQuery(sql).setParameter("tid", tenantId).getSingleResult();
		return result == null ? 0 : ((Number) result).longValue();
	}

	private long countScalarWithDate(String sql, Long tenantId, String scadenza) {
		Object result = em.createNativeQuery(sql)
			.setParameter("tid", tenantId)
			.setParameter("scadenza", scadenza)
			.getSingleResult();
		return result == null ? 0 : ((Number) result).longValue();
	}

	@SuppressWarnings("unchecked")
	private List<ChartDataDto> groupBy(String sql, Long tenantId) {
		List<Object[]> rows = em.createNativeQuery(sql).setParameter("tid", tenantId).getResultList();
		return rows.stream()
			.map(r -> new ChartDataDto(r[0] != null ? r[0].toString() : "N/D", ((Number) r[1]).longValue()))
			.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	private List<ChartGroupDataDto> buildTimeSeries(String sql, String seriesName, Long tenantId) {
		List<Object> dates = em.createNativeQuery(sql).setParameter("tid", tenantId).getResultList();

		Map<String, Long> byMonth = new LinkedHashMap<>();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");

		for (Object dateObj : dates) {
			String dateStr = dateObj.toString();
			try {
				LocalDate date = LocalDate.parse(dateStr.substring(0, 10));
				String monthKey = date.format(fmt);
				byMonth.merge(monthKey, 1L, Long::sum);
			} catch (Exception ignored) {}
		}

		List<ChartSeriesItemDto> series = byMonth.entrySet().stream()
			.sorted(Map.Entry.comparingByKey())
			.map(e -> new ChartSeriesItemDto(e.getKey(), e.getValue()))
			.collect(Collectors.toList());

		List<ChartGroupDataDto> result = new ArrayList<>();
		result.add(new ChartGroupDataDto(seriesName, series));
		return result;
	}

	@SuppressWarnings("unchecked")
	private List<ChartGroupDataDto> buildGroupedByCimitero(String sql, Long tenantId) {
		List<Object[]> rows = em.createNativeQuery(sql).setParameter("tid", tenantId).getResultList();

		Map<String, List<ChartSeriesItemDto>> grouped = new LinkedHashMap<>();
		for (Object[] r : rows) {
			String cimName = r[0] != null ? r[0].toString() : "N/D";
			String stato = r[1] != null ? r[1].toString() : "N/D";
			long count = ((Number) r[2]).longValue();
			grouped.computeIfAbsent(cimName, k -> new ArrayList<>()).add(new ChartSeriesItemDto(stato, count));
		}

		return grouped.entrySet().stream()
			.map(e -> new ChartGroupDataDto(e.getKey(), e.getValue()))
			.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	private List<ChartGroupDataDto> buildCapienzaVsOccupazione(Long tenantId, List<Long> cimiteroIds) {
		String sql = "SELECT s.nome, s.capienza, " +
			"(SELECT COUNT(*) FROM posti p2 WHERE p2.fk_struttura = s.id AND p2.stato = 'OCCUPATO') as occupati " +
			"FROM strutture s JOIN aree a ON s.fk_area = a.id JOIN cimiteri cim ON a.fk_cimitero = cim.id " +
			"WHERE cim.fk_tenant = :tid";
		if (cimiteroIds != null && !cimiteroIds.isEmpty()) {
			sql += " AND cim.id IN (" + cimiteroIds.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
		}
		sql += " ORDER BY s.nome";

		List<Object[]> rows = em.createNativeQuery(sql).setParameter("tid", tenantId).getResultList();
		List<ChartGroupDataDto> result = new ArrayList<>();
		for (Object[] r : rows) {
			String nome = r[0] != null ? r[0].toString() : "N/D";
			long capienza = r[1] != null ? ((Number) r[1]).longValue() : 0;
			long occupati = r[2] != null ? ((Number) r[2]).longValue() : 0;
			List<ChartSeriesItemDto> series = List.of(
				new ChartSeriesItemDto("Capienza", capienza),
				new ChartSeriesItemDto("Occupati", occupati)
			);
			result.add(new ChartGroupDataDto(nome, series));
		}
		return result;
	}
}
