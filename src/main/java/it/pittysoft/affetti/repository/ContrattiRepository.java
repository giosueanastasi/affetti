package it.pittysoft.affetti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;



@RepositoryRestResource()
public interface ContrattiRepository extends JpaRepository<Contratti, Integer>, JpaSpecificationExecutor<Contratti>, QuerydslPredicateExecutor<Contratti>,ContrattiRepositoryCustom {
	

	Contratti findById(Long id);

	Contratti findByDomanda(Domande domanda);

}
