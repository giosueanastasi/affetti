package it.pittysoft.affetti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Cimiteri;
import it.pittysoft.affetti.entity.Tenant;

@RepositoryRestResource()
public interface CimiteriRepository extends JpaRepository<Cimiteri, Long> {

	List<Cimiteri> findByTenant(Tenant tenant);

}
