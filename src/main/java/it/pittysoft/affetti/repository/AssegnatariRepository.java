package it.pittysoft.affetti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;



@RepositoryRestResource()
public interface AssegnatariRepository extends JpaRepository<Assegnatari, Integer>, JpaSpecificationExecutor<Assegnatari>, QuerydslPredicateExecutor<Assegnatari> {
	
	 Assegnatari findByDomanda(Domande domanda);
		
}
