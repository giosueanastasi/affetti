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
		
		if(resquestSearch.getNomeA()!=null) {
			builder.and(qAssegnatari.nome.upper().like("%"+resquestSearch.getNomeA().toUpperCase()+"%"));
		}
		if(resquestSearch.getCognomeA()!=null) {
			builder.and(qAssegnatari.cognome.upper().like("%"+resquestSearch.getCognomeA().toUpperCase()+"%"));
		}
		if(resquestSearch.getNomeC()!=null) {
			builder.and(qContraenti.nome.upper().like("%"+resquestSearch.getNomeC().toUpperCase()+"%"));
		}
		if(resquestSearch.getCognomeC()!=null) {
			builder.and(qContraenti.cognome.upper().like("%"+resquestSearch.getCognomeC().toUpperCase()+"%"));
		}
		if(resquestSearch.getData_inizio()!=null) {
			builder.and(qContratti.data_inizio.upper().like(resquestSearch.getData_inizio().toUpperCase()));
		}
		if(resquestSearch.getData_scadenza()!=null) {
			builder.and(qContratti.data_scadenza.upper().like(resquestSearch.getData_scadenza().toUpperCase()));
		}
		if(resquestSearch.getContratto() !=null) {
			builder.and(qContratti.protocollo.upper().like(resquestSearch.getContratto().getProtocollo().toUpperCase()));
		}
		if(resquestSearch.getStato()!=null) {
			builder.and(qContratti.stato.upper().like(resquestSearch.getStato().toUpperCase()));
		}
		
		
		List<Contratti> contrattiPlayer = query.select(qContratti)
		                               .from(qContratti)
//		                               .innerJoin(qContratti.domande,qDomande)
		                               .innerJoin(qDomande.contraente,qContraenti)
		                               .innerJoin(qDomande.assegnatario,qAssegnatari)
		                               .where(builder
		                            		    ).fetch();
		
		return contrattiPlayer;
		
	}

}
