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
import it.pittysoft.affetti.repository.DomandeRepository;



@Component
public class DomandeService {
	
	@Autowired
	private DomandeRepository domandeRepository;
	

	@Autowired
	private DomandaDao domandaDao;
	


	public List<Domande> getDomande() {
        return domandeRepository.findAll();
    }
    
    public Domande saveDomanda(Domande domande) {
    	return domandeRepository.save(domande);
    }
    

    
    public DomandaResponseSearch getDomande(DomandaRequestSearch resquestSearch) {
		 List<Domande> findDomandeByCognomeAndNome = domandaDao.findDomandeByCognomeAndNome(resquestSearch);
		 DomandaResponseSearch response = new DomandaResponseSearch();
		  
		 for (Domande domanda : findDomandeByCognomeAndNome) {
			 DomandaModel dm = new DomandaModel();
			 
			 dm.setDataProtocollo(domanda.getData_protocollo());
			 dm.setNumeroProtocolloDomanda(domanda.getProtocollo());
			 dm.setTipologia(domanda.getTipologia());
			 dm.setStato(domanda.getStato());	
			 dm.setCognomeContraente(domanda.getContraente().getCognome());
			 dm.setNomeContraente(domanda.getContraente().getNome());
			 dm.setAssegnatario(domanda.getContraente().getCognome()+" "+domanda.getAssegnatario().getNome());
			 dm.setNumeroProtocolloContratto(domanda.getContratto().getProtocollo());
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
			 dm.setLoculo(domanda.getPosto().getLoculo());
			 dm.setFornice(domanda.getPosto().getFornice());
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
