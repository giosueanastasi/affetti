package it.pittysoft.affetti.service;

import java.util.ArrayList;
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
import it.pittysoft.affetti.model.PostiModel;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.repository.AssegnatariRepository;
import it.pittysoft.affetti.repository.DomandeRepository;
import it.pittysoft.affetti.repository.DomandeRepositoryCustom;



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
			 dm.setCognomeA(domanda.getAssegnatario().getCognome());
			 dm.setNomeA(domanda.getAssegnatario().getNome());
			 dm.setCognomeC(domanda.getContraente().getCognome());
			 dm.setNomeC(domanda.getContraente().getNome());
			 dm.setCodice_fiscaleC(domanda.getContraente().getCodice_fiscale());
			 dm.setNumero_protocolloC(domanda.getProtocollo());
			 dm.setStatoC(domanda.getStato());	
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
    		domandaDao.addDomandaFull(request.getDomanda(), assegnatario);
    		
    	}else {
    		//errore, mancano i dati dell'assegnatario
    		response.setReturnCode(2);
    		response.setReasonCode("Mancanza campi obbligatori assegnarario");
    	}
    	
    	
    	return response;

    }

}
