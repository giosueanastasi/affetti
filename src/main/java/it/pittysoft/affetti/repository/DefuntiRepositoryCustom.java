package it.pittysoft.affetti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.pittysoft.affetti.entity.Defunti;
import it.pittysoft.affetti.model.DefuntiRequest;

@Repository
public interface DefuntiRepositoryCustom {
	
	List<Defunti> findDefuntiByNameAndOrSurname(DefuntiRequest request);
}

