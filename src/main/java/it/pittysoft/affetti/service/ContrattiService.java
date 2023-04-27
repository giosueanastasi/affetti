package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.ContrattoModel;
import it.pittysoft.affetti.model.ContrattoSearchRequest;
import it.pittysoft.affetti.model.ContrattoSearchResponse;
import it.pittysoft.affetti.model.PostiModel;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.repository.ContrattiRepository;



@Component
public class ContrattiService {
	
	private ContrattiRepository contrattiRepository;

    public ContrattiService(ContrattiRepository contrattiRepository) {
        this.contrattiRepository = contrattiRepository;
    }

    public List<Contratti> getContratti() {
        return contrattiRepository.findAll();
    }
    
    public Contratti saveContratto(Contratti contratti) {
    	return contrattiRepository.save(contratti);
    }
    
    public ContrattoSearchResponse getContratti(ContrattoSearchRequest resquestSearch) {
		 List<Contratti> findtContrattiByNomeAndCognome = contrattiRepository.findtContrattiByNomeAndCognome(resquestSearch);
		 ContrattoSearchResponse response = new ContrattoSearchResponse();
		 
		 for (Contratti contrattiFiltrati : findtContrattiByNomeAndCognome) {

			contrattiFiltrati.getDomande().size();
			
			 for (Domande domanda : contrattiFiltrati.getDomande()) {
				 ContrattoModel cm = new ContrattoModel();
				 
				 cm.setCognomeA(domanda.getAssegnatario().getCognome());
				 cm.setNomeA(domanda.getAssegnatario().getNome());
				 cm.setCognomeC(domanda.getContraente().getCognome());
				 cm.setNomeC(domanda.getContraente().getNome());
				 cm.setCodice_fiscaleC(domanda.getContraente().getCodice_fiscale());
				 cm.setProtocollo(domanda.getContratto().getProtocollo());
				 cm.setStato(domanda.getContratto().getStato());
				 cm.setData_inizio(domanda.getContratto().getData_inizio());
				 cm.setData_scadenza(domanda.getContratto().getData_scadenza());
				 response.getContratti().add(cm);
				
			 }

			 
		 }
		return response;
	}

}
