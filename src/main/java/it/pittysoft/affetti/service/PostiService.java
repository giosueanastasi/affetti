package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.repository.PostiRepository;
import it.pittysoft.affetti.repository.UsersRepository;


@Component
public class PostiService {
	
	private PostiRepository postiRepository;

    public PostiService(PostiRepository postiRepository) {
        this.postiRepository = postiRepository;
    }

    public List<Posti> getPosti() {
        return postiRepository.findAll();
    }
    
    public Posti savePosto(Posti posti) {
    	return postiRepository.save(posti);
    }

}
