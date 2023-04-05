package it.pittysoft.affetti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.pittysoft.affetti.entity.Domanda;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.links.DomandaLinks;
import it.pittysoft.affetti.links.PostoLinks;
import it.pittysoft.affetti.links.UserLinks;
import it.pittysoft.affetti.service.DomandaService;
import it.pittysoft.affetti.service.PostiService;
import it.pittysoft.affetti.service.UsersService;
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
	DomandaService domandaService;
	
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
	

    
}
