package it.pittysoft.affetti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Contraenti;


@RepositoryRestResource()
public interface ContraentiRepository extends JpaRepository<Contraenti, Integer>, JpaSpecificationExecutor<Contraenti>, QuerydslPredicateExecutor<Contraenti> {}
