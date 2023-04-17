package it.pittysoft.affetti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.core.BooleanBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.QDomande;
import it.pittysoft.affetti.entity.QPosti;

@Repository
public class PostiRepositoryCustomImpl implements PostiRepositoryCustom {
	
	@Autowired
	EntityManager em;

	@Override
	public List<Posti> findtPostiByLoculoAndFornice(Posti posti) {
		JPAQuery<Posti> query = new JPAQuery<>(em);
		QPosti qPosti = QPosti.posti;
		QDomande qDomande = QDomande.domande;
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qPosti.loculo.upper().like("%".toUpperCase()));
		builder.and(qDomande.protocollo.like("%"));
		
		List<Posti> postiPlayer = query.select(qPosti)
		                               .from(qPosti)
		                              // .innerJoin(qDomande.posto,qPosti)
		                               .where(builder
		                            		    ).fetch();
		
		return postiPlayer;
		/*	CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Posti> cq = cb.createQuery(Posti.class);
//	    Metamodel m = em.getMetamodel();
	//   EntityType<Posti> PostiMetaModel = m.entity(Posti.class);

	    Root<Posti> postofrom = cq.from(Posti.class);
	//    Join<Posti, Domande> domandeJoin = postofrom.join("fk_posto",JoinType.INNER);
	    List<Predicate> predicates = new ArrayList<>();
	    

	    
	    if (posti.getLoculo() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					postofrom.get("loculo")
	    			)
	    			, "%" + posti.getLoculo().toUpperCase() + "%"));
	    }
	    if (posti.getFornice() != null) {
	    	predicates.add(cb.like(
	    			cb.upper(
	    					postofrom.get("fornice")
	    			)
	    			, "%" + posti.getFornice().toUpperCase() + "%"));
	    }
	    cq.where(predicates.toArray(new Predicate[0]));

	    return em.createQuery(cq).getResultList();*/
	}

}
