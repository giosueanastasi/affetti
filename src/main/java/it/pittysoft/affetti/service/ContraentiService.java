package it.pittysoft.affetti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


import it.pittysoft.affetti.entity.Contraenti;
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
 
    
    public Contraenti saveContraente(Contraenti contraenti) {
    	return contraentiRepository.save(contraenti);
    }
	
	 public ContraentiResponse getContraenti(ContraentiRequest contraenti, Pageable pageable) {
		 List<Contraenti> findContraentiByCognomeAndNome = contraentiRepository.findContraentiByCognomeAndNome(contraenti);
		 ContraentiResponse response = new ContraentiResponse();
		 //Lista di contraenti model che verrà preparata ed usata per impostare l'oggetto di tipo page della response
		 List<ContraentiModel> listaContraenti = new ArrayList<>();
		 
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
			
			 
			 listaContraenti.add(pm);
			 
		 }
		 
		 //Impostiamo l'oggetto di tipo page della response
		 final int start = (int)pageable.getOffset();
	     final int end = Math.min((start + pageable.getPageSize()), listaContraenti.size());
	     final Page<ContraentiModel> page = new PageImpl<>(listaContraenti.subList(start, end), pageable, listaContraenti.size());
		 
		 response.setContraenti(page);
		 
		return response;
	}
}
