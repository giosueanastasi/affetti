package it.pittysoft.affetti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.links.PostoLinks;
import it.pittysoft.affetti.links.UserLinks;
import it.pittysoft.affetti.links.ContrattoLinks;
import it.pittysoft.affetti.links.AssegnatarioLinks;
import it.pittysoft.affetti.service.PostiService;
import it.pittysoft.affetti.service.UsersService;
import it.pittysoft.affetti.service.ContrattiService;
import it.pittysoft.affetti.service.AssegnatariService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class Controller {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	PostiService postiService;
	
	@Autowired
	AssegnatariService assegnatariService;
	
	@Autowired
	ContrattiService contrattiService;
	
	
	@GetMapping(path = UserLinks.LIST_USERS)
    public ResponseEntity<?> listUsers() {
        log.info("ApiController:  list users");
        List<Users> resource = usersService.getUsers();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = UserLinks.ADD_USER)
	public ResponseEntity<?> saveUser(@RequestBody Users user) {
        log.info("ApiController:  list users");
        Users resource = usersService.saveUser(user);
        return ResponseEntity.ok(resource);
    }
	
	@GetMapping(path = PostoLinks.LIST_POSTI)
    public ResponseEntity<?> listPosti() {
        log.info("ApiController:  list posti");
        List<Posti> resource = postiService.getPosti();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = PostoLinks.ADD_POSTO)
	public ResponseEntity<?> savePosto(@RequestBody Posti posto) {
        log.info("ApiController:  list posti");
        Posti resource = postiService.savePosto(posto);
        return ResponseEntity.ok(resource);
    }
	@GetMapping(path = AssegnatarioLinks.LIST_ASSEGNATARI)
    public ResponseEntity<?> listAssegnatari() {
        log.info("ApiController:  list assegnatari");
        List<Assegnatari> resource = assegnatariService.getAssegnatari();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = AssegnatarioLinks.ADD_ASSEGNATARIO)
	public ResponseEntity<?> saveAssegnatario(@RequestBody Assegnatari assegnatario) {
        log.info("ApiController:  list assegnatari");
        Assegnatari resource = assegnatariService.saveAssegnatario(assegnatario);
        return ResponseEntity.ok(resource);
    }
	@GetMapping(path = ContrattoLinks.LIST_CONTRATTI)
    public ResponseEntity<?> listContratti() {
        log.info("ApiController:  list contratti");
        List<Contratti> resource = contrattiService.getContratti();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = ContrattoLinks.ADD_CONTRATTO)
	public ResponseEntity<?> saveContratto(@RequestBody Contratti contratto) {
        log.info("ApiController:  list contratti");
        Contratti resource = contrattiService.saveContratto(contratto);
        return ResponseEntity.ok(resource);
    }
    
}
