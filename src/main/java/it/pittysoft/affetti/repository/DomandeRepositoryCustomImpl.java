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
	
	/*@Autowired
	EntityManager em;

	@Override
	public List<Domande> findDomandeByCognomeAndNome(DomandaRequestSearch resquestSearch) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Domande> cq = cb.createQuery(Domande.class);

	    Root<Domande> book = cq.from(Domande.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (resquestSearch.getNomeC() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					book.get("Nome")
	    			)
	    			, "%" + resquestSearch.getNomeC().toUpperCase() + "%"));
	    }
	    if (resquestSearch.getCognomeC() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					book.get("cognome")
	    			)
	    			, "%" + resquestSearch.getCognomeC().toUpperCase() + "%"));
	    }
	    if (resquestSearch.getCodice_fiscaleC() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					book.get("codice fiscale")
	    			)
	    			, "%" + resquestSearch.getCodice_fiscaleC().toUpperCase() + "%"));
	    }
	    if (resquestSearch.getEmailC() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					book.get("email")
	    			)
	    			, "%" + resquestSearch.getEmailC().toUpperCase() + "%"));
	    }
	    cq.where(predicates.toArray(new Predicate[0]));

	    return em.createQuery(cq).getResultList();
	}*/

	@Override
	public Domande addDomandaFull(Domande domanda, Assegnatari assegnatario) {
		Assegnatari ass = assegnatariRepository.save(assegnatario);
		domanda.setFk_assegnatario(ass.getId());
		domandeRepository.save(domanda);
		return domanda;
	}
	
}
