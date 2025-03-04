package it.pittysoft.affetti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Cap;

@RepositoryRestResource()
public interface CapRepository extends JpaRepository<Cap, Integer> {
	


}
