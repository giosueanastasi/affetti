package it.pittysoft.affetti.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.PostiModel;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.repository.PostiRepository;



@Component
public class PostiService {
	
	private PostiRepository postiRepository;

    public PostiService(PostiRepository postiRepository) {
        this.postiRepository = postiRepository;
    }

    public List<Posti> getPosti() {
        return postiRepository.findAll();
    }
    
    public Posti savePosto(Posti posti) {
    	return postiRepository.save(posti);
    }
    
    public PostiResponse getPosti(PostiRequest posti) {
		 List<Posti> findtPostiByLoculoAndFornice = postiRepository.findtPostiByLoculoAndFornice(posti);
		 PostiResponse response = new PostiResponse();
		 
		 for (Posti postiFiltrati : findtPostiByLoculoAndFornice) {
			 
			 
			 
//			 Iterator iter = postiFiltrati.getDomande().iterator();
//			 while (iter.hasNext()) {
//				 Domande dom = (Domande) iter.next();
//			     PostiModel pm = new PostiModel();
//				 pm.setCognome(dom.getAssegnatario().getCognome());
//				 pm.setNome(dom.getAssegnatario().getNome());
//				 pm.setLoculo(postiFiltrati.getLoculo());
//				 pm.setFornice(postiFiltrati.getFornice());
//				 response.getPosti().add(pm);
//			 }

			 postiFiltrati.getDomande().size();
			
			 for (Domande domanda : postiFiltrati.getDomande()) {
				 PostiModel pm = new PostiModel();
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
