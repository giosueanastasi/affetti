package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.AssegnatariDTO;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.ContraentiDTO;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.ContraentiRequest;
import it.pittysoft.affetti.model.ContraentiResponse;
import it.pittysoft.affetti.model.ProtocolloContrattoConAssegnatario;
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
 
    public static ContraentiDTO toDTO(Contraenti contraente) {
        if (contraente == null) return null;
        ContraentiDTO dto = new ContraentiDTO();
        dto.setId(contraente.getId());
        dto.setNome(contraente.getNome());
        dto.setComune_nascita(contraente.getComune_nascita());
        dto.setCognome(contraente.getCognome());
        dto.setProvincia_nascita(contraente.getProvincia_nascita());
        dto.setStato_nascita(contraente.getStato_nascita());
        dto.setData_nascita(contraente.getData_nascita());
        dto.setComune_residenza(contraente.getComune_residenza());
        dto.setProvincia_residenza(contraente.getProvincia_residenza());
        dto.setVia_residenza(contraente.getVia_residenza());
        dto.setCivico_residenza(contraente.getCivico_residenza());
        dto.setCap_residenza(contraente.getCap_residenza());
        dto.setTelefono(contraente.getTelefono());
        dto.setEmail(contraente.getEmail());
        dto.setCodice_fiscale(contraente.getCodice_fiscale());
        dto.setNote(contraente.getNote());
        return dto;
    }
    
    public Contraenti saveContraente(Contraenti contraenti) {
    	return contraentiRepository.save(contraenti);
    }
	
	 public ContraentiResponse getContraenti(ContraentiRequest contraenti) {
		 List<Contraenti> findContraentiByCognomeAndNome = contraentiRepository.findContraentiByCognomeAndNome(contraenti);
		 ContraentiResponse response = new ContraentiResponse();
		 
		 for (Contraenti contraente : findContraentiByCognomeAndNome) {

			 //contraentiFiltrati.getDomande().size();
			
			 ContraentiModel pm = new ContraentiModel();
			 pm.setId(contraente.getId().toString());
			 pm.setNome(contraente.getNome());
			 pm.setCognome(contraente.getCognome());
			 pm.setCodice_fiscale(contraente.getCodice_fiscale());
			 pm.setComune_nascita(contraente.getComune_nascita());
			 pm.setProvincia_nascita(contraente.getProvincia_nascita());
			 pm.setStato_nascita(contraente.getStato_nascita());
			 pm.setData_nascita(contraente.getData_nascita());
			 pm.setComune_residenza(contraente.getComune_residenza());
			 pm.setProvincia_residenza(contraente.getProvincia_residenza());
			 pm.setVia_residenza(contraente.getVia_residenza());
			 pm.setCivico_residenza(contraente.getCivico_residenza());
			 pm.setCap_residenza(contraente.getCap_residenza());
			 pm.setEmail(contraente.getEmail());
			 pm.setNote(contraente.getNote());
 			 
			 if(!contraente.getDomande().isEmpty()) {
				  for (Domande domanda : contraente.getDomande()) {
					  ProtocolloContrattoConAssegnatario contratto = new ProtocolloContrattoConAssegnatario();
					  
					  contratto.setProtocolloContratto(domanda.getContratto().getProtocollo());
					  contratto.setNomeAssegnatario(domanda.getAssegnatario().getNome());
					  contratto.setCognomeAssegnatario(domanda.getAssegnatario().getCognome());
					  
					  pm.getContratti().add(contratto);
				  }
				  pm.setProtocolloSelezionato(pm.getContratti().get(0).getProtocolloContratto());;
			 }
			
			 
			 response.getContraenti().add(pm);
			 
		 }
		return response;
	}
}
