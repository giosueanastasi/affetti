package it.pittysoft.affetti.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.model.DomandaRequestSearch;
import it.pittysoft.affetti.repository.AssegnatariRepository;
import it.pittysoft.affetti.repository.DomandeRepository;

@Component
public class DomandaDao {
	@Autowired
	private DomandeRepository domandeRepository;
	
	@Autowired
	private AssegnatariRepository assegnatariRepository;
	
	@Autowired
	EntityManager em;

	
	public List<Domande> findDomandeByCognomeAndNome(DomandaRequestSearch resquestSearch) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Domande> cq = cb.createQuery(Domande.class);

	    Root<Domande> book = cq.from(Domande.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (resquestSearch.getDomanda().getProtocollo() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					book.get("protocollo")
	    			)
	    			, "%" + resquestSearch.getDomanda().getProtocollo().toUpperCase() + "%"));
	    }
	    
	    if (resquestSearch.getDomanda().getStato() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					book.get("stato")
	    			)
	    			, "%" + resquestSearch.getDomanda().getStato().toUpperCase() + "%"));
	    }
	    
	    cq.where(predicates.toArray(new Predicate[0]));

	    return em.createQuery(cq).getResultList();
	}
	
	
	@Transactional
	public void addDomandaFull(Domande domanda, Assegnatari assegnatario) {
		Assegnatari ass = assegnatariRepository.save(assegnatario);
		domanda.setFk_assegnatario( ass.getId());
		domandeRepository.save(domanda);
	}
	

}
