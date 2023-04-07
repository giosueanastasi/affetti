package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Assegnatari;

import it.pittysoft.affetti.repository.AssegnatariRepository;



@Component
public class AssegnatariService {
	
	private AssegnatariRepository assegnatariRepository;

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
