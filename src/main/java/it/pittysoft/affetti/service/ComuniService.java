package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Comuni;
import it.pittysoft.affetti.model.ComuniModel;
import it.pittysoft.affetti.model.ComuniRequest;
import it.pittysoft.affetti.model.ComuniResponse;
import it.pittysoft.affetti.repository.ComuniRepository;



@Component
public class ComuniService {
	
	private ComuniRepository comuniRepository;

    public ComuniService(ComuniRepository comuniRepository) {
        this.comuniRepository = comuniRepository;
    }

    public ComuniResponse getComuni() {
    	
    	List<Comuni> comuni = comuniRepository.findAll();
    	ComuniResponse response = new ComuniResponse();
    	
    	for (Comuni comune : comuni) {
    		
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
    		
    		response.getComuni().add(cm);
    	}
    	
    	return response;
    }
    
    public Comuni saveComune(Comuni comuni) {
    	return comuniRepository.save(comuni);
    }

}
