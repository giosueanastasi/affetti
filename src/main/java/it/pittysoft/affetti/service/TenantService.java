package it.pittysoft.affetti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pittysoft.affetti.entity.Cimiteri;
import it.pittysoft.affetti.entity.Defunti;
import it.pittysoft.affetti.entity.Tenant;
import it.pittysoft.affetti.model.DefuntiRequest;
import it.pittysoft.affetti.repository.CimiteriRepository;
import it.pittysoft.affetti.repository.DefuntiRepository;
import it.pittysoft.affetti.repository.TenantRepository;

@Service
public class TenantService {

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private CimiteriRepository cimiteriRepository;

	@Autowired
	private DefuntiRepository defuntiRepository;

	public List<Tenant> getAllTenants() {
		return tenantRepository.findAll();
	}

	public Optional<Tenant> getTenantById(Long id) {
		return tenantRepository.findById(id);
	}

	public List<Cimiteri> getCimiteriByTenant(Long tenantId) {
		Optional<Tenant> tenant = tenantRepository.findById(tenantId);
		if (tenant.isEmpty()) {
			return List.of();
		}
		return cimiteriRepository.findByTenant(tenant.get());
	}

	public List<Defunti> searchDefuntiByTenant(Long tenantId, Long cimiteroId, DefuntiRequest request) {
		return defuntiRepository.findDefuntiByTenant(tenantId, cimiteroId, request);
	}

	public List<Defunti> getRecentDefuntiByTenant(Long tenantId, int limit) {
		return defuntiRepository.findRecentDefuntiByTenant(tenantId, limit);
	}

}
