package it.pittysoft.affetti.repository;

import java.util.List;

import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.model.ContraentiRequest;

public interface ContraentiRepositoryCustom {
	
	List<Contraenti> findContraentiByCognomeAndNome(ContraentiRequest contraenti);

}
