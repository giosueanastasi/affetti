package it.pittysoft.affetti.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Defunti;

@RepositoryRestResource()
public interface DefuntiRepository extends JpaRepository<Defunti, Long>, JpaSpecificationExecutor<Defunti>, QuerydslPredicateExecutor<Defunti>, DefuntiRepositoryCustom {
	
}
