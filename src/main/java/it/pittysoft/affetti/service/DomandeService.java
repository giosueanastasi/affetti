package it.pittysoft.affetti.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.pittysoft.affetti.dao.DomandaDao;
import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.DomandaModel;
import it.pittysoft.affetti.model.DomandaRequest;
import it.pittysoft.affetti.model.DomandaRequestSearch;
import it.pittysoft.affetti.model.DomandaResponse;
import it.pittysoft.affetti.model.DomandaResponseSearch;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.repository.AssegnatariRepository;
import it.pittysoft.affetti.repository.ContraentiRepository;
import it.pittysoft.affetti.repository.DomandeRepository;
import it.pittysoft.affetti.repository.PostiRepository;



@Component
public class DomandeService {
	
	@Autowired
	private DomandeRepository domandeRepository;
	
	@Autowired
	private PostiRepository postiRepository;
	
	@Autowired
	private AssegnatariRepository assegnatariRepository;
	
	@Autowired
	private ContraentiRepository contraentiRepository;

	@Autowired
	private DomandaDao domandaDao;
	


	public List<Domande> getDomande() {
        return domandeRepository.findAll();
    }
    
    public DomandaResponse saveDomanda(DomandaRequest domandeRequest) {
    	DomandaResponse response = new DomandaResponse();
    	
    	Posti posti = postiRepository.findById(domandeRequest.getIdPosto());
    	Contraenti contraenti = contraentiRepository.findById(domandeRequest.getIdContraente());
    	Assegnatari assegnatari = assegnatariRepository.findById(domandeRequest.getIdAssegnatario());
    	
    	Domande domande = new Domande();
    	
    	domande.setId(domandeRequest.getIdDomanda());
    	domande.setData_protocollo(domandeRequest.getDataProtocollo());
    	domande.setProtocollo(domandeRequest.getNumeroProtocolloDomanda());
    	domande.setTipologia(domandeRequest.getTipologia());
    	domande.setStato(domandeRequest.getStato());
    	domande.setPosto(posti);
    	domande.setAssegnatario(assegnatari);
    	domande.setContraente(contraenti);
    	
    	Domande domandeSaved = domandeRepository.save(domande);
    	
    	DomandaModel domandeModel = new DomandaModel();
    	
    	domandeModel.setIdDomanda(domandeSaved.getId());
    	domandeModel.setDataProtocollo(domandeSaved.getData_protocollo());
    	domandeModel.setNumeroProtocolloDomanda(domandeSaved.getProtocollo());
    	domandeModel.setTipologia(domandeSaved.getTipologia());
    	domandeModel.setStato(domandeSaved.getStato());
 
    	
    	
    	domandeModel.setIdPosto(posti.getId());
    	domandeModel.setLoculo(posti.getLoculo());
    	domandeModel.setFornice(posti.getFornice());
    	

    	
    	domandeModel.setIdContraente(contraenti.getId());
    	domandeModel.setNomeContraente(contraenti.getNome());
    	domandeModel.setCognomeContraente(contraenti.getCognome());
    	domandeModel.setComuneDiNascita(contraenti.getComune_nascita());
    	domandeModel.setProvinciaDiNascita(contraenti.getProvincia_nascita());
    	domandeModel.setStatoDiNascita(contraenti.getStato_nascita());
    	domandeModel.setComuneDiResidenza(contraenti.getComune_residenza());
    	domandeModel.setProvinciaDiResidenza(contraenti.getProvincia_residenza());
    	domandeModel.setViaDiResidenza(contraenti.getVia_residenza());
    	domandeModel.setCivicoDiResidenza(contraenti.getCivico_residenza());
    	domandeModel.setCapDiResidenza(contraenti.getCap_residenza());
    	domandeModel.setCodiceFiscale(contraenti.getCodice_fiscale());
    	domandeModel.setTelefono(contraenti.getTelefono());
    	domandeModel.setEmail(contraenti.getEmail());
    	domandeModel.setNote(contraenti.getNote());
    	

    	
    	domandeModel.setIdAssegnatario(assegnatari.getId());
    	domandeModel.setNomeAss(assegnatari.getNome());
    	domandeModel.setCognomeAss(assegnatari.getCognome());
    	domandeModel.setComuneDecesso(assegnatari.getComune_decesso());
    	domandeModel.setDataDecesso(assegnatari.getData_decesso());
    	
    	response.getDomande().add(domandeModel);
    	return response;
    	
    }
    

    
    public DomandaResponseSearch getDomande(DomandaRequestSearch resquestSearch) {
		 List<Domande> findDomandeByCognomeAndNome = domandaDao.findDomandeByCognomeAndNome(resquestSearch);
		 DomandaResponseSearch response = new DomandaResponseSearch();
		  
		 for (Domande domanda : findDomandeByCognomeAndNome) {
			 DomandaModel dm = new DomandaModel();
			 
			 dm.setIdPosto(domanda.getPosto().getId());
			 dm.setLoculo(domanda.getPosto().getLoculo());
			 dm.setFornice(domanda.getPosto().getFornice());
			 
			 dm.setIdDomanda(domanda.getId());
			 dm.setDataProtocollo(domanda.getData_protocollo());
			 dm.setNumeroProtocolloDomanda(domanda.getProtocollo());
			 dm.setTipologia(domanda.getTipologia());
			 dm.setStato(domanda.getStato());	
			 
			 dm.setNumeroProtocolloContratto(domanda.getContratto().getProtocollo());
			 
			 dm.setIdAssegnatario(domanda.getAssegnatario().getId());
			 dm.setIdContraente(domanda.getContraente().getId());
			 
			 dm.setCognomeContraente(domanda.getContraente().getCognome());
			 dm.setNomeContraente(domanda.getContraente().getNome());
			 dm.setAssegnatario(domanda.getContraente().getCognome()+" "+domanda.getAssegnatario().getNome());
			 dm.setComuneDiNascita(domanda.getContraente().getComune_nascita());
			 dm.setProvinciaDiNascita(domanda.getContraente().getProvincia_nascita());
			 dm.setStatoDiNascita(domanda.getContraente().getStato_nascita());
			 dm.setComuneDiResidenza(domanda.getContraente().getComune_residenza());
			 dm.setProvinciaDiResidenza(domanda.getContraente().getProvincia_residenza());
			 dm.setViaDiResidenza(domanda.getContraente().getVia_residenza());
			 dm.setCivicoDiResidenza(domanda.getContraente().getCivico_residenza());
			 dm.setCapDiResidenza(domanda.getContraente().getCap_residenza());
			 dm.setCodiceFiscale(domanda.getContraente().getCodice_fiscale());
			 dm.setTelefono(domanda.getContraente().getTelefono());
			 dm.setEmail(domanda.getContraente().getEmail());
			 dm.setNote(domanda.getContraente().getNote());

			 dm.setComuneDecesso(domanda.getAssegnatario().getComune_decesso());
			 dm.setDataDecesso(domanda.getAssegnatario().getData_decesso());
			 dm.setNomeAss(domanda.getAssegnatario().getNome());
			 dm.setCognomeAss(domanda.getAssegnatario().getCognome());
			 
			 response.getDomande().add(dm);
		 } 
 	return response;
		
	}

    
    
    public DomandaResponse addDomandaFull(DomandaRequest request) {
    	DomandaResponse response = new DomandaResponse();
    	Assegnatari assegnatario = new Assegnatari();
    	if(request.getCognomeAss()!=null &&
    			request.getNomeAss()!=null //TODO finire
    			) {
    		assegnatario.setCognome(request.getCognomeAss());
    		assegnatario.setNome(request.getNomeAss());
    		assegnatario.setComune_decesso(request.getComuneAss());
    		assegnatario.setData_decesso(request.getDataDecesso());
    		assegnatario.setFk_user_modifier("1");
    		assegnatario.setData_insert(null);//now
    		assegnatario.setData_update(null);
    		
    		Posti posto = new Posti();
    		posto.setLoculo(request.getLoculo());
    		posto.setFornice(request.getFornice());
    		
    		Contraenti contraente = new Contraenti();
    		contraente.setId(Long.parseLong(request.getFkContraente()));
    		
    		
    		domandaDao.addDomandaFull(request.getDomanda(), assegnatario, posto,contraente);
    		
    	}else {
    		//errore, mancano i dati dell'assegnatario
    		response.setReturnCode(2);
    		response.setReasonCode("Mancanza campi obbligatori assegnarario");
    	}
    	
    	
    	return response;

    }

}
