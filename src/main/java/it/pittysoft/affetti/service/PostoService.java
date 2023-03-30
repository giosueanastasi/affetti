package it.pittysoft.affetti.service;

import java.util.List;

import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Posto;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.repository.PostoRepository;
import it.pittysoft.affetti.repository.UsersRepository;


@Component
public class PostoService {
	
	private PostoRepository postoRepository;

    public PostoService(PostoRepository postoRepository) {
        this.postoRepository = postoRepository;
    }

    public List<Posto> getPosti() {
        return postoRepository.findAll();
    }
    
    public Posto savePosto(Posto posto) {
    	return postoRepository.save(posto);
    }

}
