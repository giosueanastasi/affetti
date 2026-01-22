package it.pittysoft.affetti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.TipiSepoltura;

@RepositoryRestResource()
public interface TipiSepolturaRepository extends JpaRepository<TipiSepoltura, Integer>, JpaSpecificationExecutor<TipiSepoltura>, QuerydslPredicateExecutor<TipiSepoltura> {

	TipiSepoltura findById(Long id);
	TipiSepoltura findByCodice(String codice);
	List<TipiSepoltura> findByAttivoOrderByCodice(Boolean attivo);

}
