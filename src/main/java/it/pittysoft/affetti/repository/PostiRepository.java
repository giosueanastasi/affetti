package it.pittysoft.affetti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.Users;


@RepositoryRestResource()
public interface PostiRepository extends JpaRepository<Posti, Integer>, JpaSpecificationExecutor<Posti>, QuerydslPredicateExecutor<Posti> {}
