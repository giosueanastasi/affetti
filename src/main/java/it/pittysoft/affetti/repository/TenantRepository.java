package it.pittysoft.affetti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Tenant;

@RepositoryRestResource()
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	java.util.Optional<Tenant> findBySlug(String slug);

	boolean existsBySlug(String slug);

}
