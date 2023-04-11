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

import it.pittysoft.affetti.entity.Contraenti;

@Repository
public class ContraentiRepositoryCustomImpl implements ContraentiRepositoryCustom {
	@Autowired
	EntityManager em;

	@Override
	public List<Contraenti> findContraentiByCognomeAndNome(Contraenti contraenti) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Contraenti> cq = cb.createQuery(Contraenti.class);

	    Root<Contraenti> book = cq.from(Contraenti.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    if (contraenti.getCognome() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					book.get("cognome")
	    			)
	    			, "%" + contraenti.getCognome().toUpperCase() + "%"));
	    }
	    if (contraenti.getNome() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					book.get("nome")
	    			)
	    			, "%" + contraenti.getNome().toUpperCase() + "%"));
	    }
	    cq.where(predicates.toArray(new Predicate[0]));

	    return em.createQuery(cq).getResultList();
	}

}
