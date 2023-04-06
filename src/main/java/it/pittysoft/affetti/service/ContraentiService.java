package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Contraenti;
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

}
