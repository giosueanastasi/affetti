package it.pittysoft.affetti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;



@RepositoryRestResource()
public interface PostiRepository extends JpaRepository<Posti, Integer>, JpaSpecificationExecutor<Posti>, QuerydslPredicateExecutor<Posti>, PostiRepositoryCustom {
	
	List<Posti> findByForniceAndLoculoOrderById(String fornice,String loculo);
	Posti findById (Long id);
	//Posti findByDomanda(Domande domande);
	
}
