package it.pittysoft.affetti.repository;

import java.util.List;

import it.pittysoft.affetti.entity.Contraenti;

public interface ContraentiRepositoryCustom {
	
	List<Contraenti> findContraentiByCognomeAndNome(Contraenti contraenti);

}
