package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Comuni;
import it.pittysoft.affetti.model.ComuniModel;
import it.pittysoft.affetti.model.ComuniResponse;
import it.pittysoft.affetti.model.ComuniSelectModel;
import it.pittysoft.affetti.model.ComuniSelectResponse;
import it.pittysoft.affetti.repository.ComuniRepository;



@Component
public class ComuniService {
	
	private ComuniRepository comuniRepository;

    public ComuniService(ComuniRepository comuniRepository) {
        this.comuniRepository = comuniRepository;
    }

    public ComuniSelectResponse getComuni() {
    	
    	List<Comuni> comuni = comuniRepository.findAll();
    	ComuniSelectResponse response = new ComuniSelectResponse();
    	
    	for (Comuni comune : comuni) {
    		
    		ComuniSelectModel cm = new ComuniSelectModel();
    		cm.setId(comune.getId());
    		cm.setNome(comune.getNome());
    		cm.setProvincia(comune.getProvincia());
    		cm.setStato(comune.getStato());
    		
    		response.getComuni().add(cm);
    	}
    	
    	return response;
    }
    
    public Comuni saveComune(Comuni comuni) {
    	return comuniRepository.save(comuni);
    }
    
    public ComuniResponse getComune(String nome) {
    	
    	ComuniResponse response =  new ComuniResponse();
    	
    	//Recuperiamo il comune ricercato tramite il nome
    	Comuni comune = comuniRepository.findByNomeContainingIgnoreCase(nome).getFirst();
    	
    	//Usiamo l'entity appena recuperata per compilare i campi del model
    	ComuniModel cm = new ComuniModel();
    	cm.setId(comune.getId());
    	cm.setNome(comune.getNome());
    	cm.setCodComuneIstat(comune.getCodComuneIstat());
    	cm.setCodCatastComune(comune.getCodCatastComune());
    	cm.setProvincia(comune.getProvincia());
    	cm.setCodProv(comune.getCodProv());
    	cm.setDescProv(comune.getDescProv());
    	cm.setCodReg(comune.getCodReg());
    	cm.setDescReg(comune.getDescReg());
    	
    	response.setComune(cm);
    	
    	return response;  	
    }

}
