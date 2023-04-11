package it.pittysoft.affetti.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Domande;

@Repository
public class DomandeRepositoryCustomImpl implements DomandeRepositoryCustom {

	@Autowired
	private DomandeRepository domandeRepository;
	
	@Autowired
	private AssegnatariRepository assegnatariRepository;
	
	@Override
	public Domande addDomandaFull(Domande domanda, Assegnatari assegnatario) {
		Assegnatari ass = assegnatariRepository.save(assegnatario);
		domanda.setFk_assegnatario(ass.getId());
		domandeRepository.save(domanda);
		return domanda;
	}
	
}
