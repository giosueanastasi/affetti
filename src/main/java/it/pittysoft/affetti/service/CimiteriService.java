package it.pittysoft.affetti.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pittysoft.affetti.entity.Cimiteri;
import it.pittysoft.affetti.entity.Tenant;
import it.pittysoft.affetti.repository.CimiteriRepository;
import it.pittysoft.affetti.repository.TenantRepository;

@Service
public class CimiteriService {

	@Autowired
	private CimiteriRepository cimiteriRepository;

	@Autowired
	private TenantRepository tenantRepository;

	public List<Cimiteri> getCimiteriByTenant(Long tenantId) {
		Optional<Tenant> tenant = tenantRepository.findById(tenantId);
		if (tenant.isEmpty()) {
			return List.of();
		}
		return cimiteriRepository.findByTenant(tenant.get());
	}

	public Optional<Cimiteri> getCimiteroById(Long id) {
		return cimiteriRepository.findById(id);
	}

	public Cimiteri saveCimitero(Cimiteri cimitero) {
		String now = LocalDate.now().toString();
		if (cimitero.getId() == null) {
			cimitero.setData_insert(now);
		}
		cimitero.setData_update(now);
		return cimiteriRepository.save(cimitero);
	}

	public void deleteCimitero(Long id) {
		cimiteriRepository.deleteById(id);
	}

}
