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

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.QAssegnatari;
import it.pittysoft.affetti.entity.QContraenti;
import it.pittysoft.affetti.entity.QDomande;
import it.pittysoft.affetti.entity.QPosti;
import it.pittysoft.affetti.model.DomandaRequestSearch;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.repository.AssegnatariRepository;
import it.pittysoft.affetti.repository.DomandeRepository;

@Component
public class DomandaDao {
	@Autowired
	private DomandeRepository domandeRepository;

	@Autowired
	EntityManager em;

	
	public List<Domande> findDomandeByCognomeAndNome(DomandaRequestSearch resquestSearch) {
		JPAQuery<Domande> query = new JPAQuery<>(em);
		QDomande qDomande = QDomande.domande;
		QContraenti qContraenti = QContraenti.contraenti;
		QAssegnatari qAssegnatari = QAssegnatari.assegnatari;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(resquestSearch.getDomanda() !=null && resquestSearch.getDomanda().getProtocollo()!=null) {
			builder.and(qDomande.protocollo.upper().like(resquestSearch.getDomanda().getProtocollo().toUpperCase()));
		}
		if(resquestSearch.getDomanda() !=null && resquestSearch.getDomanda().getStato()!=null ) {
			builder.and(qDomande.stato.upper().like(resquestSearch.getDomanda().getStato().toUpperCase()));
		}
		if(resquestSearch.getNomeC()!=null) {
			builder.and(qContraenti.nome.upper().like("%"+resquestSearch.getNomeC().toUpperCase()+"%"));
		}
		if(resquestSearch.getCognomeC()!=null) {
			builder.and(qContraenti.cognome.upper().like("%"+resquestSearch.getCognomeC().toUpperCase()+"%"));
		}
		
		if(resquestSearch.getNomeA()!=null) {
			builder.and(qAssegnatari.nome.upper().like("%"+resquestSearch.getNomeA().toUpperCase()+"%"));
		}
		if(resquestSearch.getCognomeA()!=null) {
			builder.and(qAssegnatari.cognome.upper().like("%"+resquestSearch.getCognomeA().toUpperCase()+"%"));
		}
		
		if(resquestSearch.getCodice_fiscaleC()!=null) {
			builder.and(qContraenti.codice_fiscale.upper().like( resquestSearch.getCodice_fiscaleC().toUpperCase()));
		}
		
		List<Domande> domandePlayer = query.select(qDomande)
		                               .from(qDomande)
		                               .innerJoin(qDomande.assegnatario,qAssegnatari)
		                               .innerJoin(qDomande.contraente,qContraenti)
		                               .where(builder
		                            		    ).fetch();
		
		return domandePlayer;
	}	
	
	
	@Transactional
	public void addDomandaFull(Domande domanda, Assegnatari assegnatario) {
		domanda.setAssegnatario(assegnatario);
		domandeRepository.save(domanda);
	}
	

}
