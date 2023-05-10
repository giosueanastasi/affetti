package it.pittysoft.affetti.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.PostiModel;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.repository.ContraentiRepository;
import it.pittysoft.affetti.repository.ContrattiRepository;
import it.pittysoft.affetti.repository.DomandeRepository;
import it.pittysoft.affetti.repository.PostiRepository;



@Component
public class PostiService {
	
	private PostiRepository postiRepository;
	
	@Autowired
	private DomandeRepository domandaRepository;
	
	@Autowired
	private ContrattiRepository contrattiRepository;
	
    public PostiService(PostiRepository postiRepository) {
        this.postiRepository = postiRepository;
    }

    public List<Posti> getPosti() {
        return postiRepository.findAll();
    }
    
    public PostiResponse savePosto(PostiRequest postiRequest) {
    	PostiResponse response = new PostiResponse();
    	
    	Posti posti = new Posti();
    	posti.setId(postiRequest.getId());
    	posti.setLoculo(postiRequest.getLoculo());
    	posti.setFornice(postiRequest.getFornice());
    	posti.setStato(postiRequest.getStato());
    	   	
    	Posti postiSaved = postiRepository.save(posti);
    	
    	PostiModel postiModel = new PostiModel();
    	postiModel.setId(postiSaved.getId());
    	postiModel.setLoculo(postiSaved.getLoculo());
    	postiModel.setFornice(postiSaved.getFornice());
    	postiModel.setStato(postiSaved.getStato());
    	
    	Domande domanda = domandaRepository.findById(postiRequest.getIdDomanda());
    	postiModel.setIdDomanda(domanda.getId());
    	postiModel.setCognome(domanda.getAssegnatario().getCognome());
    	postiModel.setNome(domanda.getAssegnatario().getNome());
   	
    	Contratti contratti = contrattiRepository.findByDomanda(domanda);
    	postiModel.setScadenza(contratti.getData_scadenza());
    	
    	   	
    	response.getPosti().add(postiModel);
    	
    	
    	return response;
    }
    
    public PostiResponse getPosti(PostiRequest posti) {
		 List<Posti> findtPostiByLoculoAndFornice = postiRepository.findtPostiByLoculoAndFornice(posti);
		 PostiResponse response = new PostiResponse();
		 
		 for (Posti postiFiltrati : findtPostiByLoculoAndFornice) {

			 postiFiltrati.getDomande().size();
			
			 for (Domande domanda : postiFiltrati.getDomande()) {
				 PostiModel pm = new PostiModel();
				 pm.setId(postiFiltrati.getId());
				 pm.setIdDomanda(domanda.getId());
				 pm.setCognome(domanda.getAssegnatario().getCognome());
				 pm.setNome(domanda.getAssegnatario().getNome());
				 pm.setLoculo(postiFiltrati.getLoculo());
				 pm.setFornice(postiFiltrati.getFornice());
				 pm.setStato(postiFiltrati.getStato());
				 pm.setScadenza(domanda.getContratto().getData_scadenza());
				 response.getPosti().add(pm);
				
			 }
			 
		 }
		return response;
	}

}
