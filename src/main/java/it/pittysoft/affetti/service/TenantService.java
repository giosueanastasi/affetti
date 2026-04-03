package it.pittysoft.affetti.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@PersistenceContext
	private EntityManager entityManager;

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

	public Tenant saveTenant(Tenant tenant) {
		if (tenant.getSlug() == null || tenant.getSlug().isBlank()) {
			tenant.setSlug(generateSlug(tenant.getDescrizione()));
		} else {
			tenant.setSlug(normalizeSlug(tenant.getSlug()));
			if (tenant.getId() == null && tenantRepository.existsBySlug(tenant.getSlug())) {
				tenant.setSlug(makeUniqueSlug(tenant.getSlug()));
			}
		}
		return tenantRepository.save(tenant);
	}

	private String generateSlug(String descrizione) {
		String base = normalizeSlug(descrizione);
		return tenantRepository.existsBySlug(base) ? makeUniqueSlug(base) : base;
	}

	private String normalizeSlug(String input) {
		return input.toLowerCase()
				.replaceAll("[àáâãäå]", "a")
				.replaceAll("[èéêë]", "e")
				.replaceAll("[ìíîï]", "i")
				.replaceAll("[òóôõö]", "o")
				.replaceAll("[ùúûü]", "u")
				.replaceAll("[^a-z0-9\\s-]", "")
				.trim()
				.replaceAll("\\s+", "-");
	}

	private String makeUniqueSlug(String baseSlug) {
		int counter = 2;
		String slug = baseSlug + "-" + counter;
		while (tenantRepository.existsBySlug(slug)) {
			counter++;
			slug = baseSlug + "-" + counter;
		}
		return slug;
	}

	public Tenant updateTenant(Long id, Tenant updated) {
		Tenant existing = tenantRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Tenant non trovato"));
		existing.setDescrizione(updated.getDescrizione());
		existing.setLogoUrl(updated.getLogoUrl());
		existing.setColorePrimario(updated.getColorePrimario());
		existing.setColoreSecondario(updated.getColoreSecondario());
		existing.setSinossi(updated.getSinossi());
		if (updated.getSlug() != null && !updated.getSlug().isBlank()) {
			String newSlug = normalizeSlug(updated.getSlug());
			if (!newSlug.equals(existing.getSlug()) && tenantRepository.existsBySlug(newSlug)) {
				newSlug = makeUniqueSlug(newSlug);
			}
			existing.setSlug(newSlug);
		}
		return tenantRepository.save(existing);
	}

	@Transactional
	public void deleteTenant(Long tenantId) {
		// Ordine di pulizia: contratti -> domande -> defunti -> posti -> strutture -> aree -> cimiteri -> users.fk_tenant -> tenant
		// 1. Contratti (via domande che referenziano posti del tenant)
		entityManager.createNativeQuery(
			"DELETE FROM contratti WHERE fk_domanda IN (SELECT d.id FROM domande d WHERE d.fk_posto IN (SELECT p.id FROM posti p WHERE p.fk_tenant = :tid))")
			.setParameter("tid", tenantId).executeUpdate();
		// 2. Domande
		entityManager.createNativeQuery(
			"DELETE FROM domande WHERE fk_posto IN (SELECT p.id FROM posti p WHERE p.fk_tenant = :tid)")
			.setParameter("tid", tenantId).executeUpdate();
		// 3. Defunti
		entityManager.createNativeQuery(
			"DELETE FROM defunti WHERE fk_posto IN (SELECT p.id FROM posti p WHERE p.fk_tenant = :tid)")
			.setParameter("tid", tenantId).executeUpdate();
		// 4. Posti (diretti via fk_tenant)
		entityManager.createNativeQuery("DELETE FROM posti WHERE fk_tenant = :tid")
			.setParameter("tid", tenantId).executeUpdate();
		// 5. Strutture (via aree dei cimiteri del tenant)
		entityManager.createNativeQuery(
			"DELETE FROM strutture WHERE fk_area IN (SELECT a.id FROM aree a WHERE a.fk_cimitero IN (SELECT c.id FROM cimiteri c WHERE c.fk_tenant = :tid))")
			.setParameter("tid", tenantId).executeUpdate();
		// 6. Aree
		entityManager.createNativeQuery(
			"DELETE FROM aree WHERE fk_cimitero IN (SELECT c.id FROM cimiteri c WHERE c.fk_tenant = :tid)")
			.setParameter("tid", tenantId).executeUpdate();
		// 7. Cimiteri
		entityManager.createNativeQuery("DELETE FROM cimiteri WHERE fk_tenant = :tid")
			.setParameter("tid", tenantId).executeUpdate();
		// 8. Sgancia utenti (non li eliminiamo, solo null su fk_tenant)
		entityManager.createNativeQuery("UPDATE users SET fk_tenant = NULL WHERE fk_tenant = :tid")
			.setParameter("tid", tenantId).executeUpdate();
		// 9. Tenant
		entityManager.createNativeQuery("DELETE FROM tenant WHERE id = :tid")
			.setParameter("tid", tenantId).executeUpdate();
	}

	public String saveLogoFile(Long tenantId, org.springframework.web.multipart.MultipartFile file) throws IOException {
		String filename = "tenant-" + tenantId + "-" + file.getOriginalFilename();
		Path uploadDir = Paths.get("src/main/resources/static/assets/tenants");
		Files.createDirectories(uploadDir);
		Path filePath = uploadDir.resolve(filename);
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		return "assets/tenants/" + filename;
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
