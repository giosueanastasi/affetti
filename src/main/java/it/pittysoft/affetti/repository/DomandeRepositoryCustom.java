package it.pittysoft.affetti.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Domande;

@RepositoryRestResource()
public interface DomandeRepositoryCustom {
	 @Transactional
	 Domande addDomandaFull(Domande domanda, Assegnatari assegnatario);
}
