package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;
import it.pittysoft.affetti.entity.Contratti;
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

}
