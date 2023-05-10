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
			 cm.setCodice_fiscale(contratti.getDomanda().getContraente().getCodice_fiscale());
			 cm.setComune_nascita(contratti.getDomanda().getContraente().getComune_nascita());
			 cm.setProvincia_nascita(contratti.getDomanda().getContraente().getProvincia_nascita());
			 cm.setComune_nascita(contratti.getDomanda().getContraente().getComune_nascita());
			 cm.setStato_nascita(contratti.getDomanda().getContraente().getStato_nascita());
			 cm.setData_nascita(contratti.getDomanda().getContraente().getData_nascita());
			 cm.setComune_residenza(contratti.getDomanda().getContraente().getComune_residenza());
			 cm.setProvincia_residenza(contratti.getDomanda().getContraente().getProvincia_residenza());
			 cm.setVia_residenza(contratti.getDomanda().getContraente().getVia_residenza());
			 cm.setCivico_residenza(contratti.getDomanda().getContraente().getCivico_residenza());
			 cm.setCap_residenza(contratti.getDomanda().getContraente().getCap_residenza());
			 cm.setEmail(contratti.getDomanda().getContraente().getEmail());
			 cm.setNote(contratti.getDomanda().getContraente().getNote());
			 cm.setTelefono(contratti.getDomanda().getContraente().getTelefono());
			 cm.setLoculo(contratti.getDomanda().getPosto().getLoculo());
			 cm.setFornice(contratti.getDomanda().getPosto().getFornice());
			 cm.setComune_decesso(contratti.getDomanda().getAssegnatario().getComune_decesso());
			 cm.setData_decesso(contratti.getDomanda().getAssegnatario().getData_decesso());
			 cm.setProtocolloDomanda(contratti.getDomanda().getProtocollo());
			 cm.setDataProtocolloDomanda(contratti.getDomanda().getData_protocollo());
			 cm.setCognomeC(contratti.getDomanda().getContraente().getCognome());
			 cm.setNomeC(contratti.getDomanda().getContraente().getNome());
			 cm.setNomeA(contratti.getDomanda().getAssegnatario().getNome());
			 cm.setCognomeA(contratti.getDomanda().getAssegnatario().getCognome());
			response.getContratti().add(cm);

			 
		 }
		return response;
	}

}
