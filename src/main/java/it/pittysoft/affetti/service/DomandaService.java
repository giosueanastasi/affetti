package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Domanda;
import it.pittysoft.affetti.entity.Posto;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.repository.DomandaRepository;
import it.pittysoft.affetti.repository.PostoRepository;
import it.pittysoft.affetti.repository.UsersRepository;


@Component
public class DomandaService {
	
	private DomandaRepository domandaRepository;

    public DomandaService(DomandaRepository domandaRepository) {
        this.domandaRepository = domandaRepository;
    }

    public List<Domanda> getDomande() {
        return domandaRepository.findAll();
    }
    
    public Domanda saveDomanda(Domanda domanda) {
    	return domandaRepository.save(domanda);
    }

}
