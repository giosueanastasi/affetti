package it.pittysoft.affetti.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;


@RepositoryRestResource()
public interface ContraentiRepository extends JpaRepository<Contraenti, Integer>, JpaSpecificationExecutor<Contraenti>, QuerydslPredicateExecutor<Contraenti>, ContraentiRepositoryCustom {
	Contraenti findById(Long id);
	Contraenti findByDomanda(Domande domanda);
	
}
