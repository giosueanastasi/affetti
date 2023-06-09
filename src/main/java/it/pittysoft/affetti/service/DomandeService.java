package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.pittysoft.affetti.dao.DomandaDao;
import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.model.DomandaRequest;
import it.pittysoft.affetti.model.DomandaRequestSearch;
import it.pittysoft.affetti.model.DomandaResponse;
import it.pittysoft.affetti.model.DomandaResponseSearch;
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
    
    public List<Domande> getDomande(DomandaRequestSearch resquestSearch) {
    	DomandaResponseSearch responseSearch = new DomandaResponseSearch();
    	if(resquestSearch.getNomeC()!=null &&
    			resquestSearch.getCognomeC()!=null && 
    			resquestSearch.getCodice_fiscaleC()!=null &&
    			resquestSearch.getEmailC()!=null &&
    			resquestSearch.getData_protocollo_inizialeC()!=null &&
    			resquestSearch.getData_protocollo_finaleC()!=null &&
    			resquestSearch.getNumero_protocolloC()!=null
    			) {
    		//errore, mancano i dati dell'assegnatario
    		responseSearch.setReturnCode(2);
    		responseSearch.setReasonCode("Inserire almeno un campo");
    	}
    	return domandaDao.findDomandeByCognomeAndNome(resquestSearch);
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
