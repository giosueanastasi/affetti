package it.pittysoft.affetti.repository;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.model.DomandaRequestSearch;

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
