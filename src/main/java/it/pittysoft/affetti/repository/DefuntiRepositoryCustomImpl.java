package it.pittysoft.affetti.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import it.pittysoft.affetti.entity.Defunti;
import it.pittysoft.affetti.entity.QDefunti;
import it.pittysoft.affetti.model.DefuntiRequest;

@Repository
public class DefuntiRepositoryCustomImpl implements DefuntiRepositoryCustom {
	
	@Autowired
	private EntityManager em;
	

	@Override
	public List<Defunti> findDefuntiByNameAndOrSurname(DefuntiRequest request) {
	    QDefunti qDefunti = QDefunti.defunti;
	    BooleanBuilder builder = new BooleanBuilder();

	    if (request != null && !request.getRicerca().trim().isEmpty()) {
	        String[] parts = request.getRicerca().trim().toUpperCase().split("\\s+");

	        for (String part : parts) {
	            String search = "%" + part + "%";

	            builder.and(
	                qDefunti.nome.upper().like(search)
	                .or(qDefunti.cognome.upper().like(search))
	            );
	        }
	    }

	    return new JPAQuery<Defunti>(em)
	            .select(qDefunti)
	            .from(qDefunti)
	            .where(builder)
	            .fetch();
	}


}


