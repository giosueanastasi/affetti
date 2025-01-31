package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Comuni;
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
    		
    		response.getComuni().add(cm);
    	}
    	
    	return response;
    }
    
    public Comuni saveComune(Comuni comuni) {
    	return comuniRepository.save(comuni);
    }

}
