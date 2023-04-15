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

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.QContraenti;
import it.pittysoft.affetti.entity.QUsers;

@Repository
public class ContraentiRepositoryCustomImpl implements ContraentiRepositoryCustom {
	@Autowired
	EntityManager em;

	@Override
	public List<Contraenti> findContraentiByCognomeAndNome(Contraenti contraenti) {
		
		JPAQuery<Contraenti> query = new JPAQuery<>(em);
		QContraenti qContraenti = QContraenti.contraenti;
		QUsers qUsers = QUsers.users;
		
		//https://thorben-janssen.com/querydsl-hibernate/
		//http://querydsl.com/static/querydsl/latest/reference/html/ch03.html
		
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qContraenti.nome.upper().like("%anto%".toUpperCase()));
		builder.and(qUsers.username.like("Stefano24"));
		
		List<Contraenti> contraentiPlayer = query.select(qContraenti)
		                               .from(qContraenti)
		                               .innerJoin(qContraenti.user,qUsers)
		                               .where(builder
		                            		    ).fetch();
		
		return contraentiPlayer;
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//	    CriteriaQuery<Contraenti> cq = cb.createQuery(Contraenti.class);
//
//	    Root<Contraenti> book = cq.from(Contraenti.class);
//	    List<Predicate> predicates = new ArrayList<>();
//	    
//	    if (contraenti.getCognome() != null) {
//	    	predicates.add(cb.like(
//	    			cb.upper(
//	    					book.get("cognome")
//	    			)
//	    			, "%" + contraenti.getCognome().toUpperCase() + "%"));
//	    }
//	    if (contraenti.getNome() != null) {
//	    	predicates.add(cb.like(
//	    			cb.upper(
//	    					book.get("nome")
//	    			)
//	    			, "%" + contraenti.getNome().toUpperCase() + "%"));
//	    }
//	    cq.where(predicates.toArray(new Predicate[0]));
//
//	    return em.createQuery(cq).getResultList();
	}

}
