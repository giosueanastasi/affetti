package it.pittysoft.affetti.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.ContrattoModel;
import it.pittysoft.affetti.model.ContrattoSearchRequest;
import it.pittysoft.affetti.model.ContrattoSearchResponse;
import it.pittysoft.affetti.model.PostiModel;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.repository.AssegnatariRepository;
import it.pittysoft.affetti.repository.ContraentiRepository;
import it.pittysoft.affetti.repository.ContrattiRepository;
import it.pittysoft.affetti.repository.DomandeRepository;
import it.pittysoft.affetti.repository.PostiRepository;
import lombok.Data;



@Component
public class ContrattiService {

	private ContrattiRepository contrattiRepository;

	private DomandeRepository domandaRepository;

	private ContraentiRepository contraentiRepository;

	private AssegnatariRepository assegnatariRepository;

	private PostiRepository postiRepository;

	
	
	public ContrattiService(ContrattiRepository contrattiRepository, DomandeRepository domandaRepository,
			ContraentiRepository contraentiRepository, AssegnatariRepository assegnatariRepository,
			PostiRepository postiRepository) {
		super();
		this.contrattiRepository = contrattiRepository;
		this.domandaRepository = domandaRepository;
		this.contraentiRepository = contraentiRepository;
		this.assegnatariRepository = assegnatariRepository;
		this.postiRepository = postiRepository;
	}

	public List<Contratti> getContratti() {
		return contrattiRepository.findAll();
	}

	public Contratti saveContratto(Contratti contratti) {
		return contrattiRepository.save(contratti);
	}

	public ContrattoSearchResponse saveContratto(ContrattoModel contrattiRequest) {
		ContrattoSearchResponse response = new ContrattoSearchResponse();

		Contratti contratti = contrattiRepository.findById(contrattiRequest.getIdContratto());
//		contratti.setId(contrattiRequest.getIdContratto());
		contratti.setProtocollo(contrattiRequest.getNumeroProtocolloContratto());
//    	contratti.setStato(contrattiRequest.getStato());
//    	contratti.setData_inizio(contrattiRequest.getDataProtocolloContratto());
//    	contratti.setData_scadenza(contrattiRequest.getDataProtocolloContratto());

		contrattiRepository.save(contratti);

//    	ContrattoModel contrattiModel = new ContrattoModel();
//    	contrattiModel.setIdContratto(contrattiSaved.getId());
//    	contrattiModel.setStato(contrattiSaved.getStato());
//    	contrattiModel.setNumeroProtocolloContratto(contrattiSaved.getProtocollo());
//    	contrattiModel.setDataProtocolloContratto(contrattiSaved.getData_inizio());
//    	contrattiModel.setDataScadenzaContratto(contrattiSaved.getData_scadenza());
//    	
//    	
//    	Domande domanda = domandaRepository.findById(contrattiRequest.getIdDomanda());
//    	contrattiModel.setIdDomanda(domanda.getId());
//    	contrattiModel.setProtocolloDomanda(domanda.getProtocollo());
//    	contrattiModel.setDataProtocolloDomanda(domanda.getData_protocollo());
//    	
//    	Posti posto = postiRepository.findById(domanda.getPosto().getId());
//    	contrattiModel.setIdPosto(domanda.getId());
//    	contrattiModel.setLoculo(posto.getLoculo());
//    	contrattiModel.setFornice(posto.getFornice()); 
//   	
//    	Contraenti contraente = contraentiRepository.findById(domanda.getContraente().getId());
//    	contrattiModel.setIdContraente(domanda.getId());
//    	contrattiModel.setNomeC(contraente.getNome());
//    	contrattiModel.setCognomeC(contraente.getCognome());
//    	contrattiModel.setCap_residenza(contraente.getCap_residenza());
//    	contrattiModel.setCivico_residenza(contraente.getCivico_residenza());
//    	contrattiModel.setCodice_fiscale(contraente.getCodice_fiscale());
//    	contrattiModel.setComune_nascita(contraente.getComune_nascita());
//    	contrattiModel.setComune_residenza(contraente.getComune_residenza());
//    	contrattiModel.setEmail(contraente.getEmail());
//    	contrattiModel.setNote(contraente.getNote());
//    	contrattiModel.setProvincia_nascita(contraente.getProvincia_nascita());
//    	contrattiModel.setProvincia_residenza(contraente.getProvincia_residenza());
//    	contrattiModel.setStato_nascita(contraente.getStato_nascita());
//    	contrattiModel.setTelefono(contraente.getTelefono());
//    	contrattiModel.setVia_residenza(contraente.getVia_residenza());
//    	contrattiModel.setData_nascita(contraente.getData_nascita());
//    	
//    	
//    	Assegnatari assegnatari = assegnatariRepository.findById(domanda.getAssegnatario().getId());
//    	contrattiModel.setIdAssegnatario(domanda.getId());
//    	contrattiModel.setData_decesso(assegnatari.getData_decesso());
//    	contrattiModel.setComune_decesso(assegnatari.getComune_decesso()); 
//    	contrattiModel.setNomeA(assegnatari.getNome());
//    	contrattiModel.setCognomeA(assegnatari.getCognome());

		response.getContratti().add(contrattiRequest);

		return response;
	}

	public ContrattoSearchResponse getContratti(ContrattoSearchRequest resquestSearch) {
		List<Contratti> findtContrattiByNomeAndCognome = contrattiRepository
				.findtContrattiByNomeAndCognome(resquestSearch);
		ContrattoSearchResponse response = new ContrattoSearchResponse();

		for (Contratti contratti : findtContrattiByNomeAndCognome) {
			ContrattoModel cm = new ContrattoModel();
			cm.setNumeroProtocolloContratto(contratti.getProtocollo());
			cm.setStato(contratti.getStato());
			cm.setDataProtocolloContratto(contratti.getData_inizio());
			cm.setDataScadenzaContratto(contratti.getData_scadenza());
			cm.setContraente(contratti.getDomanda().getContraente().getCognome() + " "
					+ contratti.getDomanda().getContraente().getNome());
			cm.setAssegnatario(contratti.getDomanda().getAssegnatario().getCognome() + " "
					+ contratti.getDomanda().getAssegnatario().getNome());
			cm.setCodice_fiscale(contratti.getDomanda().getContraente().getCodice_fiscale());
			cm.setComune_nascita(contratti.getDomanda().getContraente().getComune_nascita());
			cm.setProvincia_nascita(contratti.getDomanda().getContraente().getProvincia_nascita());
			cm.setComune_nascita(contratti.getDomanda().getContraente().getComune_nascita());
			cm.setStato_nascita(contratti.getDomanda().getContraente().getStato_nascita());
			cm.setData_nascita(contratti.getDomanda().getContraente().getData_nascita());
			cm.setComune_residenza(contratti.getDomanda().getContraente().getComune_residenza());
			cm.setProvincia_residenza(contratti.getDomanda().getContraente().getProvincia_residenza());
			cm.setVia_residenza(contratti.getDomanda().getContraente().getVia_residenza());
			cm.setCivico_residenza(contratti.getDomanda().getContraente().getCivico_residenza());
			cm.setCap_residenza(contratti.getDomanda().getContraente().getCap_residenza());
			cm.setEmail(contratti.getDomanda().getContraente().getEmail());
			cm.setNote(contratti.getDomanda().getContraente().getNote());
			cm.setTelefono(contratti.getDomanda().getContraente().getTelefono());
			cm.setLoculo(contratti.getDomanda().getPosto().getLoculo());
			cm.setFornice(contratti.getDomanda().getPosto().getFornice());
			cm.setComune_decesso(contratti.getDomanda().getAssegnatario().getComune_decesso());
			cm.setData_decesso(contratti.getDomanda().getAssegnatario().getData_decesso());
			cm.setProtocolloDomanda(contratti.getDomanda().getProtocollo());
			cm.setDataProtocolloDomanda(contratti.getDomanda().getData_protocollo());
			cm.setCognomeC(contratti.getDomanda().getContraente().getCognome());
			cm.setNomeC(contratti.getDomanda().getContraente().getNome());
			cm.setNomeA(contratti.getDomanda().getAssegnatario().getNome());
			cm.setCognomeA(contratti.getDomanda().getAssegnatario().getCognome());
			cm.setIdPosto(contratti.getDomanda().getPosto().getId());
			cm.setIdAssegnatario(contratti.getDomanda().getAssegnatario().getId());
			cm.setIdContraente(contratti.getDomanda().getContraente().getId());
			cm.setIdContratto(contratti.getId());

			response.getContratti().add(cm);

		}
		return response;
	}
	
	public ContrattoSearchResponse getContrattoByProtocollo(String numProtocollo) {
		
		ContrattoSearchResponse response = new ContrattoSearchResponse();
		Contratti contratti = contrattiRepository.findByProtocollo(numProtocollo);
		ContrattoModel cm = new ContrattoModel();
		cm.setNumeroProtocolloContratto(contratti.getProtocollo());
		cm.setStato(contratti.getStato());
		cm.setDataProtocolloContratto(contratti.getData_inizio());
		cm.setDataScadenzaContratto(contratti.getData_scadenza());
		cm.setContraente(contratti.getDomanda().getContraente().getCognome() + " "
				+ contratti.getDomanda().getContraente().getNome());
		cm.setAssegnatario(contratti.getDomanda().getAssegnatario().getCognome() + " "
				+ contratti.getDomanda().getAssegnatario().getNome());
		cm.setCodice_fiscale(contratti.getDomanda().getContraente().getCodice_fiscale());
		cm.setComune_nascita(contratti.getDomanda().getContraente().getComune_nascita());
		cm.setProvincia_nascita(contratti.getDomanda().getContraente().getProvincia_nascita());
		cm.setComune_nascita(contratti.getDomanda().getContraente().getComune_nascita());
		cm.setStato_nascita(contratti.getDomanda().getContraente().getStato_nascita());
		cm.setData_nascita(contratti.getDomanda().getContraente().getData_nascita());
		cm.setComune_residenza(contratti.getDomanda().getContraente().getComune_residenza());
		cm.setProvincia_residenza(contratti.getDomanda().getContraente().getProvincia_residenza());
		cm.setVia_residenza(contratti.getDomanda().getContraente().getVia_residenza());
		cm.setCivico_residenza(contratti.getDomanda().getContraente().getCivico_residenza());
		cm.setCap_residenza(contratti.getDomanda().getContraente().getCap_residenza());
		cm.setEmail(contratti.getDomanda().getContraente().getEmail());
		cm.setNote(contratti.getDomanda().getContraente().getNote());
		cm.setTelefono(contratti.getDomanda().getContraente().getTelefono());
		cm.setLoculo(contratti.getDomanda().getPosto().getLoculo());
		cm.setFornice(contratti.getDomanda().getPosto().getFornice());
		cm.setComune_decesso(contratti.getDomanda().getAssegnatario().getComune_decesso());
		cm.setData_decesso(contratti.getDomanda().getAssegnatario().getData_decesso());
		cm.setProtocolloDomanda(contratti.getDomanda().getProtocollo());
		cm.setDataProtocolloDomanda(contratti.getDomanda().getData_protocollo());
		cm.setCognomeC(contratti.getDomanda().getContraente().getCognome());
		cm.setNomeC(contratti.getDomanda().getContraente().getNome());
		cm.setNomeA(contratti.getDomanda().getAssegnatario().getNome());
		cm.setCognomeA(contratti.getDomanda().getAssegnatario().getCognome());
		cm.setIdPosto(contratti.getDomanda().getPosto().getId());
		cm.setIdAssegnatario(contratti.getDomanda().getAssegnatario().getId());
		cm.setIdContraente(contratti.getDomanda().getContraente().getId());
		cm.setIdContratto(contratti.getId());
		
		response.getContratti().add(cm);
		
		return response;
	}

}
