package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.ContraentiRequest;
import it.pittysoft.affetti.model.ContraentiResponse;
import it.pittysoft.affetti.model.ContraentiModel;
import it.pittysoft.affetti.model.ContraentiRequest;
import it.pittysoft.affetti.model.ContraentiResponse;
import it.pittysoft.affetti.repository.ContraentiRepository;


@Component
public class ContraentiService {
	
	private ContraentiRepository contraentiRepository;

    public ContraentiService(ContraentiRepository contraentiRepository) {
        this.contraentiRepository = contraentiRepository;
    }

    public List<Contraenti> getContraenti() {
        return contraentiRepository.findAll();
    }
 
    
    public Contraenti saveContraente(Contraenti contraenti) {
    	return contraentiRepository.save(contraenti);
    }
	
	 public ContraentiResponse getContraenti(ContraentiRequest contraenti) {
		 List<Contraenti> findContraentiByCognomeAndNome = contraentiRepository.findContraentiByCognomeAndNome(contraenti);
		 ContraentiResponse response = new ContraentiResponse();
		 
		 for (Contraenti contraentiFiltrati : findContraentiByCognomeAndNome) {

			 contraentiFiltrati.getDomande().size();
			
			 for (Domande domanda : contraentiFiltrati.getDomande()) {
				 ContraentiModel pm = new ContraentiModel();
				 pm.setId(contraentiFiltrati.getId().toString());
				 pm.setNome(contraentiFiltrati.getNome());
				 pm.setCognome(contraentiFiltrati.getCognome());
				 pm.setCodice_fiscale(contraentiFiltrati.getCodice_fiscale());
				 pm.setComune_nascita(contraentiFiltrati.getComune_nascita());
				 pm.setProvincia_nascita(contraentiFiltrati.getProvincia_nascita());
				 pm.setStato_nascita(contraentiFiltrati.getStato_nascita());
				 pm.setData_nascita(contraentiFiltrati.getData_nascita());
				 pm.setComune_residenza(contraentiFiltrati.getComune_residenza());
				 pm.setProvincia_residenza(contraentiFiltrati.getProvincia_residenza());
				 pm.setVia_residenza(contraentiFiltrati.getVia_residenza());
				 pm.setCivico_residenza(contraentiFiltrati.getCivico_residenza());
				 pm.setCap_residenza(contraentiFiltrati.getCap_residenza());
				 pm.setEmail(contraentiFiltrati.getEmail());
				 pm.setNote(contraentiFiltrati.getNote());
     			 pm.setProtocolloC(domanda.getContratto().getProtocollo());
				 pm.setNomeAss(domanda.getAssegnatario().getNome());
				 pm.setCognomeAss(domanda.getAssegnatario().getCognome());
				 
				 response.getContraenti().add(pm);
				
			 }

			 
		 }
		return response;
	}
}
