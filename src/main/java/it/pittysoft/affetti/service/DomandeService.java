package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;


import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.repository.DomandeRepository;



@Component
public class DomandeService {
	
	private DomandeRepository domandeRepository;

    public DomandeService(DomandeRepository domandeRepository) {
        this.domandeRepository = domandeRepository;
    }

    public List<Domande> getDomande() {
        return domandeRepository.findAll();
    }
    
    public Domande saveDomanda(Domande domande) {
    	return domandeRepository.save(domande);
    }

}
