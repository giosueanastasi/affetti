package it.pittysoft.affetti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.pittysoft.affetti.entity.Comuni;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.links.ComuneLinks;
import it.pittysoft.affetti.links.ContraenteLinks;
import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.links.PostoLinks;
import it.pittysoft.affetti.links.UserLinks;
import it.pittysoft.affetti.service.ComuniService;
import it.pittysoft.affetti.service.ContraentiService;
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
	ContraentiService contraentiService;
	
	@Autowired
	ComuniService comuniService;
	
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
    
	
	@GetMapping(path = ContraenteLinks.LIST_CONTRAENTI)
    public ResponseEntity<?> listContraenti() {
        log.info("ApiController:  list contraenti");
        List<Contraenti> resource = contraentiService.getContraenti();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = ContraenteLinks.ADD_CONTRAENTE)
	public ResponseEntity<?> saveContraente(@RequestBody Contraenti contraente) {
        log.info("ApiController:  list contraenti");
        Contraenti resource = contraentiService.saveContraente(contraente);
        return ResponseEntity.ok(resource);
    }
	
	@GetMapping(path = ComuneLinks.LIST_COMUNI)
    public ResponseEntity<?> listComuni() {
        log.info("ApiController:  list Comuni");
        List<Comuni> resource = comuniService.getComuni();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = ComuneLinks.ADD_COMUNE)
	public ResponseEntity<?> saveComune(@RequestBody Comuni comune) {
        log.info("ApiController:  list comuni");
        Comuni resource = comuniService.saveComune(comune);
        return ResponseEntity.ok(resource);
	}
}
