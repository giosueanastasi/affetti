package it.pittysoft.affetti.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.model.DomandaRequestSearch;

@RepositoryRestResource()
public interface DomandeRepositoryCustom {
	
	 @Transactional
	 Domande addDomandaFull(Domande domanda, Assegnatari assegnatario);
	 
	 //List<Domande> findDomandeByCognomeAndNome(DomandaRequestSearch resquestSearch);
}
