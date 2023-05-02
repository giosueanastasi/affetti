package it.pittysoft.affetti.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.QAssegnatari;
import it.pittysoft.affetti.entity.QContraenti;
import it.pittysoft.affetti.entity.QContratti;
import it.pittysoft.affetti.entity.QDomande;
import it.pittysoft.affetti.entity.QPosti;
import it.pittysoft.affetti.model.DomandaRequestSearch;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.repository.AssegnatariRepository;
import it.pittysoft.affetti.repository.ContraentiRepository;
import it.pittysoft.affetti.repository.ContrattiRepository;
import it.pittysoft.affetti.repository.DomandeRepository;
import it.pittysoft.affetti.repository.PostiRepository;

@Component
public class DomandaDao {
	@Autowired
	private DomandeRepository domandeRepository;
	
	@Autowired
	private PostiRepository postiRepository;
	
	@Autowired
	private AssegnatariRepository assegnatariRepository;

	@Autowired
	private ContraentiRepository contraentiRepository;
	
	@Autowired
	private ContrattiRepository contrattiRepository;

	@Autowired
	EntityManager em;

	
	public List<Domande> findDomandeByCognomeAndNome(DomandaRequestSearch resquestSearch) {
		JPAQuery<Domande> query = new JPAQuery<>(em);
		QDomande qDomande = QDomande.domande;
		QContraenti qContraenti = QContraenti.contraenti;
		QAssegnatari qAssegnatari = QAssegnatari.assegnatari;
		QContratti qContratti = QContratti.contratti;
		
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
		
		List<Domande> domandePlayer = query.select(qDomande)
		                               .from(qDomande)
		                               .innerJoin(qDomande.assegnatario,qAssegnatari)
		                               .innerJoin(qDomande.contraente,qContraenti)
		                               .innerJoin(qDomande.contratto,qContratti)
		                               .where(builder
		                            		    ).fetch();
		
		return domandePlayer;
	}	
	
	
	@Transactional
	public void addDomandaFull(Domande domanda, Assegnatari assegnatario, Posti posto, Contraenti contraente) {
		List<Posti> posti = postiRepository.findByForniceAndLoculoOrderById(posto.getFornice(),posto.getLoculo());
		Contraenti contraenteN = contraentiRepository.findById(contraente.getId());
		//WARN fare tutti i controlli per vetire errari
		domanda.setPosto(posti.get(0));
		assegnatariRepository.save(assegnatario);
		domanda.setContraente(contraenteN);
		domanda.setAssegnatario(assegnatario);
		domandeRepository.save(domanda);
		Contratti contratto = new Contratti();
		contratto.setDomanda(domanda);
		contratto.setStato("IN_ATTESA_PAGAMENTO");
		Calendar cal = Calendar.getInstance();
		Date dataMorte = assegnatario.getData_decesso();
		cal.setTime(dataMorte);
		cal.add(Calendar.YEAR, 35); 
		Date dataScadenzaContratto = cal.getTime();
		contratto.setData_inizio(dataMorte);
		contratto.setData_scadenza(dataScadenzaContratto);
		contrattiRepository.save(contratto);
	}
	

}
