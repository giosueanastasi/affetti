package it.pittysoft.affetti.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pittysoft.affetti.dto.DefuntoCardDto;
import it.pittysoft.affetti.entity.Cimiteri;
import it.pittysoft.affetti.entity.Defunti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Tenant;
import it.pittysoft.affetti.model.DefuntiRequest;
import it.pittysoft.affetti.repository.CimiteriRepository;
import it.pittysoft.affetti.repository.DefuntiRepository;
import it.pittysoft.affetti.repository.DomandeRepository;
import it.pittysoft.affetti.repository.TenantRepository;

@Service
public class TenantService {

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private CimiteriRepository cimiteriRepository;

	@Autowired
	private DefuntiRepository defuntiRepository;

	@Autowired
	private DomandeRepository domandeRepository;

	public List<Tenant> getAllTenants() {
		return tenantRepository.findAll();
	}

	public Optional<Tenant> getTenantById(Long id) {
		return tenantRepository.findById(id);
	}

	public Optional<Tenant> getTenantBySlug(String slug) {
		return tenantRepository.findBySlug(slug);
	}

	public List<Cimiteri> getCimiteriByTenant(Long tenantId) {
		Optional<Tenant> tenant = tenantRepository.findById(tenantId);
		if (tenant.isEmpty()) {
			return List.of();
		}
		return cimiteriRepository.findByTenant(tenant.get());
	}

	public List<Defunti> searchDefuntiByTenant(Long tenantId, List<Long> cimiteroIds, DefuntiRequest request) {
		return defuntiRepository.findDefuntiByTenant(tenantId, cimiteroIds, request);
	}

	public List<Defunti> getRecentDefuntiByTenant(Long tenantId, List<Long> cimiteroIds, int limit) {
		return defuntiRepository.findRecentDefuntiByTenant(tenantId, cimiteroIds, limit);
	}

	public List<DefuntoCardDto> enrichDefuntiCards(List<Defunti> defunti) {
		List<Long> postoIds = defunti.stream()
				.filter(d -> d.getPosto() != null)
				.map(d -> d.getPosto().getId())
				.collect(Collectors.toList());

		Map<Long, Domande> domandeByPostoId = domandeRepository.findByPosto_IdIn(postoIds)
				.stream()
				.collect(Collectors.toMap(
						dom -> dom.getPosto().getId(),
						dom -> dom,
						(existing, replacement) -> existing
				));

		return defunti.stream().map(defunto -> {
			DefuntoCardDto dto = new DefuntoCardDto();
			dto.setId(defunto.getId());
			dto.setCodice(defunto.getCodice());
			dto.setNome(defunto.getNome());
			dto.setCognome(defunto.getCognome());
			dto.setData_nascita(defunto.getData_nascita());
			dto.setComune_nascita(defunto.getComune_nascita());
			dto.setProvincia_nascita(defunto.getProvincia_nascita());
			dto.setData_decesso(defunto.getData_decesso());
			dto.setComune_decesso(defunto.getComune_decesso());
			dto.setProvincia_decesso(defunto.getProvincia_decesso());
			dto.setImmagine_url(defunto.getImmagine_url());
			dto.setElogio_funebre(defunto.getElogio_funebre());

			if (defunto.getPosto() != null) {
				Domande domanda = domandeByPostoId.get(defunto.getPosto().getId());
				if (domanda != null) {
					dto.setDomandaId(domanda.getId());
					if (domanda.getContratto() != null) {
						dto.setContrattoId(domanda.getContratto().getId());
					}
				}
			}
			return dto;
		}).collect(Collectors.toList());
	}

}
