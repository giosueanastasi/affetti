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
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.QAssegnatari;
import it.pittysoft.affetti.entity.QContraenti;
import it.pittysoft.affetti.entity.QContratti;
import it.pittysoft.affetti.entity.QDomande;
import it.pittysoft.affetti.entity.QPosti;
import it.pittysoft.affetti.model.ContraentiRequest;
import it.pittysoft.affetti.model.PostiRequest;

@Repository
public class ContraentiRepositoryCustomImpl implements ContraentiRepositoryCustom {
	@Autowired
	EntityManager em;

	@Override

	public List<Contraenti> findContraentiByCognomeAndNome(ContraentiRequest contraenti) {
		JPAQuery<Contraenti> query = new JPAQuery<>(em);
		QContraenti qContraenti = QContraenti.contraenti;
		QDomande qDomande = QDomande.domande;
		QContratti qContratti = QContratti.contratti;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(contraenti.getNome()!=null) {
			builder.and(qContraenti.nome.upper().like("%" + contraenti.getNome().toUpperCase()+"%" ));
		}
		if(contraenti.getCognome()!=null) {
			builder.and(qContraenti.cognome.upper().like("%" + contraenti.getCognome().toUpperCase()+ "%" ));
			
		}
		if(contraenti.getCodice_fiscale()!=null) {
			builder.and(qContraenti.codice_fiscale.upper().like(contraenti.getCodice_fiscale().toUpperCase()));
		}
		if(contraenti.getProtocolloC()!=null) {
			builder.and(qContratti.protocollo.upper().like(contraenti.getProtocolloC().toUpperCase()));
			
		}
		

		
		
		List<Contraenti> contraentiPlayer = query.select(qContraenti)
		                               .from(qContraenti)
		                               .innerJoin(qContraenti.domande,qDomande)
		                               .innerJoin(qDomande.contratto,qContratti)
		                               .where(builder
		                            		    ).fetch();
		
		return contraentiPlayer;
}
	}
