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
import it.pittysoft.affetti.entity.QAssegnatari;
import it.pittysoft.affetti.entity.QContratti;
import it.pittysoft.affetti.entity.QDomande;
import it.pittysoft.affetti.entity.QPosti;
import it.pittysoft.affetti.model.PostiRequest;

@Repository
public class PostiRepositoryCustomImpl implements PostiRepositoryCustom {
	
	@Autowired
	EntityManager em;

	@Override
	public List<Posti> findtPostiByLoculoAndFornice(PostiRequest posti) {
		JPAQuery<Posti> query = new JPAQuery<>(em);
		QPosti qPosti = QPosti.posti;
		QDomande qDomande = QDomande.domande;
		QAssegnatari qAssegnatari = QAssegnatari.assegnatari;
		QContratti qContratti = QContratti.contratti;

		
		BooleanBuilder builder = new BooleanBuilder();
		

		
		if(posti.getNome()!=null && !posti.getNome().isEmpty()) {
			builder.and(qAssegnatari.nome.upper().like(posti.getNome().toUpperCase()));
		}
		if(posti.getCognome()!=null && !posti.getCognome().isEmpty()) {
			builder.and(qAssegnatari.cognome.upper().like(posti.getCognome().toUpperCase()));
		}
		if(posti.getFornice()!=null && !posti.getFornice().isEmpty()) {
			builder.and(qPosti.fornice.upper().like(posti.getFornice().toUpperCase()));
		}
		if(posti.getLoculo()!=null && !posti.getLoculo().isEmpty()) {
			builder.and(qPosti.loculo.upper().like(posti.getLoculo().toUpperCase()));
		}
		if(posti.getStato()!=null&& !posti.getStato().isEmpty()) {
			builder.and(qPosti.stato.upper().like(posti.getStato().toUpperCase()));
		}
		
		if(posti.getData_inizio()!=null && posti.getData_scadenza()!=null) {
			builder.and(qContratti.data_scadenza.between(posti.getData_inizio(), posti.getData_scadenza()));
		}
		
		
		
		List<Posti> postiPlayer = query.select(qPosti)
		                               .from(qPosti)
		                               .innerJoin(qPosti.domande,qDomande)
		                               .innerJoin(qDomande.assegnatario,qAssegnatari)
		                               .innerJoin(qDomande.contratto,qContratti)
		                               .where(builder
		                            		    ).fetch();
		
		return postiPlayer;
	}
	
	

}
