package it.pittysoft.affetti.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.repository.AssegnatariRepository;
import it.pittysoft.affetti.repository.DomandeRepository;

@Component
public class DomandaDao {
	@Autowired
	private DomandeRepository domandeRepository;

	
	@Transactional
	public void addDomandaFull(Domande domanda, Assegnatari assegnatario) {
		domanda.setAssegnatario(assegnatario);
		domandeRepository.save(domanda);
	}
	

}
