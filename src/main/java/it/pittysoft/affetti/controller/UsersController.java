package it.pittysoft.affetti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.pittysoft.affetti.entity.Contraente;
import it.pittysoft.affetti.entity.Posto;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.links.ContraenteLinks;
import it.pittysoft.affetti.links.PostoLinks;
import it.pittysoft.affetti.links.UserLinks;
import it.pittysoft.affetti.service.ContraenteService;
import it.pittysoft.affetti.service.PostoService;
import it.pittysoft.affetti.service.UsersService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	PostoService postoService;
	
	@Autowired
	ContraenteService contraenteService;
	
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
        List<Posto> resource = postoService.getPosti();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = PostoLinks.ADD_POSTO)
	public ResponseEntity<?> savePosto(@RequestBody Posto posto) {
        log.info("ApiController:  list posti");
        Posto resource = postoService.savePosto(posto);
        return ResponseEntity.ok(resource);
    }
	
	
	@GetMapping(path = ContraenteLinks.LIST_CONTRAENTI)
    public ResponseEntity<?> listContraenti() {
        log.info("ApiController:  list contraenti");
        List<Contraente> resource = contraenteService.getContraenti();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = ContraenteLinks.ADD_CONTRAENTE)
	public ResponseEntity<?> saveContraente(@RequestBody Contraente contraente) {
        log.info("ApiController:  list posti");
        Contraente resource = contraenteService.saveContraente(contraente);
        return ResponseEntity.ok(resource);
    }
}