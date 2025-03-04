package it.pittysoft.affetti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import it.pittysoft.affetti.entity.Comuni;


@RepositoryRestResource()
public interface ComuniRepository extends JpaRepository<Comuni, Integer>, JpaSpecificationExecutor<Comuni>, QuerydslPredicateExecutor<Comuni>, ComuniRepositoryCustom {
	List<Comuni> findByNomeContainingIgnoreCase(String nome);
}
