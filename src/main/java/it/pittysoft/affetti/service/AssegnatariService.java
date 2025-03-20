package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.AssegnatariDTO;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.PostiDTO;
import it.pittysoft.affetti.repository.AssegnatariRepository;



@Component
public class AssegnatariService {
	
	private AssegnatariRepository assegnatariRepository;
	
	 public static AssegnatariDTO toDTO(Assegnatari assegnatario) {
	        if (assegnatario == null) return null;
	        AssegnatariDTO dto = new AssegnatariDTO();
	        dto.setId(assegnatario.getId());
	        dto.setNome(assegnatario.getNome());
	        dto.setCognome(assegnatario.getCognome());
	        return dto;
	    }

    public AssegnatariService(AssegnatariRepository assegnatariRepository) {
        this.assegnatariRepository = assegnatariRepository;
    }

    public List<Assegnatari> getAssegnatari() {
        return assegnatariRepository.findAll();
    }
    
    public Assegnatari saveAssegnatario(Assegnatari assegnatari) {
    	return assegnatariRepository.save(assegnatari);
    }

}
