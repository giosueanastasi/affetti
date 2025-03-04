package it.pittysoft.affetti.repository;

import java.util.List;

import it.pittysoft.affetti.entity.Comuni;
import it.pittysoft.affetti.model.ComuniRequest;

public interface ComuniRepositoryCustom {
	List<Comuni> findByNomeContainingIgnoreCase(ComuniRequest comuni);
}
