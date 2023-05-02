package it.pittysoft.affetti.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		 
		 for (Contratti contratti : findtContrattiByNomeAndCognome) {
			ContrattoModel cm = new ContrattoModel();
			 cm.setNumeroProtocolloContratto(contratti.getProtocollo());
			 cm.setStato(contratti.getStato());	
			 cm.setDataProtocolloContratto(contratti.getData_inizio());
			 cm.setDataScadenzaContratto(contratti.getData_scadenza());
			 cm.setContraente(contratti.getDomanda().getContraente().getCognome()+ " "
					 +contratti.getDomanda().getContraente().getNome());
			 cm.setAssegnatario(contratti.getDomanda().getAssegnatario().getCognome()+ " "
					 +contratti.getDomanda().getAssegnatario().getNome());
			response.getContratti().add(cm);

			 
		 }
		return response;
	}

}
