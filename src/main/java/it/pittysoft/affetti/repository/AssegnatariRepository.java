package it.pittysoft.affetti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Assegnatari;



@RepositoryRestResource()
public interface AssegnatariRepository extends JpaRepository<Assegnatari, Integer>, JpaSpecificationExecutor<Assegnatari>, QuerydslPredicateExecutor<Assegnatari> {}
