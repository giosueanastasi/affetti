package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Contraente;
import it.pittysoft.affetti.entity.Posto;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.repository.ContraenteRepository;
import it.pittysoft.affetti.repository.PostoRepository;
import it.pittysoft.affetti.repository.UsersRepository;


@Component
public class ContraenteService {
	
	private ContraenteRepository contraenteRepository;

    public ContraenteService(ContraenteRepository contraenteRepository) {
        this.contraenteRepository = contraenteRepository;
    }

    public List<Contraente> getContraenti() {
        return contraenteRepository.findAll();
    }
    
    public Contraente saveContraente(Contraente contraente) {
    	return contraenteRepository.save(contraente);
    }

}
