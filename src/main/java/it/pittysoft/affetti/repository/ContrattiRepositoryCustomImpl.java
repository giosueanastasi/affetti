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

import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.QAssegnatari;
import it.pittysoft.affetti.entity.QContraenti;
import it.pittysoft.affetti.entity.QContratti;
import it.pittysoft.affetti.entity.QDomande;
import it.pittysoft.affetti.entity.QPosti;
import it.pittysoft.affetti.model.ContrattoSearchRequest;
import it.pittysoft.affetti.model.PostiRequest;

@Repository
public class ContrattiRepositoryCustomImpl implements ContrattiRepositoryCustom {
	
	@Autowired
	EntityManager em;

	
	public List<Contratti> findtContrattiByNomeAndCognome(ContrattoSearchRequest resquestSearch) {
		JPAQuery<Contratti> query = new JPAQuery<>(em);
		QContraenti qContraenti = QContraenti.contraenti;
		QAssegnatari qAssegnatari = QAssegnatari.assegnatari;
		QContratti qContratti = QContratti.contratti;
		QDomande qDomande = QDomande.domande;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(resquestSearch.getNumeroProtocollo() !=null && !resquestSearch.getNumeroProtocollo().isEmpty()) {
			builder.and(qDomande.protocollo.upper().like(resquestSearch.getNumeroProtocollo().toUpperCase()));
		}
		if(resquestSearch.getStato() !=null  && !resquestSearch.getStato().isEmpty() ) {
			builder.and(qDomande.stato.upper().like(resquestSearch.getStato().toUpperCase()));
		}
		if(resquestSearch.getNome()!=null && !resquestSearch.getNome().isEmpty()) {
			builder.and(qContraenti.nome.upper().like("%"+resquestSearch.getNome().toUpperCase()+"%"));
		}
		if(resquestSearch.getCognome()!=null && !resquestSearch.getCognome().isEmpty()) {
			builder.and(qContraenti.cognome.upper().like("%"+resquestSearch.getCognome().toUpperCase()+"%"));
		}
			
		if(resquestSearch.getCodiceFiscale()!=null && !resquestSearch.getCodiceFiscale().isEmpty()) {
			builder.and(qContraenti.codice_fiscale.upper().like(resquestSearch.getCodiceFiscale().toUpperCase()));
		}
		
		
		List<Contratti> contrattiPlayer = query.select(qContratti)
		                               .from(qContratti)
		                               .innerJoin(qContratti.domanda,qDomande)
		                               .innerJoin(qDomande.contraente,qContraenti)
		                               .innerJoin(qDomande.assegnatario,qAssegnatari)
		                               .where(builder
		                            		    ).fetch();
		
		return contrattiPlayer;
		
	}

}
