package it.pittysoft.affetti.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.spec.SecretKeySpec;

import com.lowagie.text.DocumentException;

import freemarker.template.TemplateException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Cap;
import it.pittysoft.affetti.entity.Comuni;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.links.AssegnatarioLinks;
import it.pittysoft.affetti.links.CapLinks;
import it.pittysoft.affetti.links.ComuneLinks;
import it.pittysoft.affetti.links.ContraenteLinks;
import it.pittysoft.affetti.links.ContrattoLinks;
import it.pittysoft.affetti.links.DomandaLinks;
import it.pittysoft.affetti.links.PostoLinks;
import it.pittysoft.affetti.links.UserLinks;
import it.pittysoft.affetti.model.CapResponse;
import it.pittysoft.affetti.model.ComuniSelectResponse;
import it.pittysoft.affetti.model.ContraentiRequest;
import it.pittysoft.affetti.model.ContraentiResponse;
import it.pittysoft.affetti.model.ContrattoModel;
import it.pittysoft.affetti.model.ContrattoSearchRequest;
import it.pittysoft.affetti.model.ContrattoSearchResponse;
import it.pittysoft.affetti.model.DomandaRequest;
import it.pittysoft.affetti.model.DomandaRequestSearch;
import it.pittysoft.affetti.model.DomandaResponse;
import it.pittysoft.affetti.model.DomandaResponseSearch;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.model.ProtocolloDomandaResponse;
import it.pittysoft.affetti.model.Response;
import it.pittysoft.affetti.model.UserRequest;
import it.pittysoft.affetti.model.UserResponse;
import it.pittysoft.affetti.security.AuthRequest;
import it.pittysoft.affetti.security.AuthResponse;
import it.pittysoft.affetti.service.AssegnatariService;
import it.pittysoft.affetti.service.ComuniService;
import it.pittysoft.affetti.service.ContraentiService;
import it.pittysoft.affetti.service.ContrattiService;
import it.pittysoft.affetti.service.DomandeService;
import it.pittysoft.affetti.service.PostiService;
import it.pittysoft.affetti.service.UsersService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class ControllerPrincipale {
	
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
	
	@Autowired
	DomandeService domandeService;
	
    @Autowired
    AuthenticationManager authenticationManager;
    
	
    String key = "chiave-segreta-temporanea-abbastanza-lunga-0123456789"; 
    Key signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
	
	
	@GetMapping(path = UserLinks.LIST_USERS)
    public ResponseEntity<?> listUsers() {
        log.info("ApiController:  list users");
        List<Users> resource = usersService.getUsers();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = UserLinks.SEARCH_USERS)
    public ResponseEntity<?> searchUsers(@RequestBody UserRequest user) {
		UserResponse resource = usersService.getUsers(user);
        if (resource.getReturnCode()==Response.OK) {
        	return ResponseEntity.ok(resource);
        } else  {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
		}     
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
	public ResponseEntity<?> savePosto(@RequestBody PostiRequest posto) {
        log.info("ApiController:  list posti");
        PostiResponse resource = postiService.savePosto(posto);
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
	public ResponseEntity<?> saveContratto(@RequestBody ContrattoModel contratto) {
        log.info("ApiController:  list contratti");
        ContrattoSearchResponse resource = contrattiService.saveContratto(contratto);
        return ResponseEntity.ok(resource);
    }
    

	@GetMapping(path = ContraenteLinks.LIST_CONTRAENTI)
    public ResponseEntity<?> listContraenti() {
        log.info("ApiController:  list contraenti");
        List<Contraenti> resource = contraentiService.getContraenti();
        return ResponseEntity.ok(resource);
    }
	
	
	@PostMapping(path = ContraenteLinks.SEARCH_CONTRAENTI)
    public ResponseEntity<?> searchContraenti(@RequestBody ContraentiRequest contraenti,
    		@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
        log.info("ApiController:  search contraenti");
        ContraentiResponse resource = (ContraentiResponse) contraentiService.getContraenti(contraenti, pageable);
        if (resource.getReturnCode()==Response.OK) {
        	return ResponseEntity.ok(resource);
        } else  {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
		}   
        }
	
	@PostMapping(path = DomandaLinks.SEARCH_DOMANDE)
    public ResponseEntity<?> searchDomande(@RequestBody DomandaRequestSearch resquestSearch) {
        log.info("ApiController:  search domande");
        DomandaResponseSearch resource = domandeService.getDomande(resquestSearch);
        if (resource.getReturnCode()==Response.OK) {
        	return ResponseEntity.ok(resource);
        } else  {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
        }
	}
	
	@PostMapping(path = PostoLinks.SEARCH_POSTI)
    public ResponseEntity<?> searchPosti(@RequestBody PostiRequest posti) {
        log.info("ApiController:  search posti");
        PostiResponse resource = postiService.getPosti(posti);
        if (resource.getReturnCode()==Response.OK) {
        	return ResponseEntity.ok(resource);
        } else  {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
		}
	}
	
	@PostMapping(path = ContrattoLinks.SEARCH_CONTRATTO)
    public ResponseEntity<?> searchContratto(@RequestBody ContrattoSearchRequest resquestSearch) {
        log.info("ApiController:  search contratti");
        ContrattoSearchResponse resource = contrattiService.getContratti(resquestSearch);
        if (resource.getReturnCode()==Response.OK) {
        	return ResponseEntity.ok(resource);
        } else  {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
		}
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
        ComuniSelectResponse resource = comuniService.getComuni();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = ComuneLinks.ADD_COMUNE)
	public ResponseEntity<?> saveComune(@RequestBody Comuni comune) {
        log.info("ApiController:  list comuni");
        Comuni resource = comuniService.saveComune(comune);
        return ResponseEntity.ok(resource);
	}
	
	
	@GetMapping(path = DomandaLinks.LIST_DOMANDE)
    public ResponseEntity<?> listDomande() {
        log.info("ApiController:  list domande");
        List<Domande> resource = domandeService.getDomande();
        return ResponseEntity.ok(resource);
    }
	
	@PostMapping(path = DomandaLinks.ADD_DOMANDA)
	public ResponseEntity<?> saveDomanda(@RequestBody Domande domanda) {
        log.info("ApiController:  list domande");
        Domande resource = domandeService.saveDomanda(domanda);
        return ResponseEntity.ok(resource);
        
        /*
         * 	@PostMapping(path = PostoLinks.ADD_POSTO)
	public ResponseEntity<?> savePosto(@RequestBody PostiRequest posto) {
        log.info("ApiController:  list posti");
        PostiResponse resource = postiService.savePosto(posto);
        return ResponseEntity.ok(resource);
         */
    }
	
	@PostMapping(path = DomandaLinks.ADD_DOMANDA_FULL)
	public ResponseEntity<?> addDomandaFull(@RequestBody DomandaRequest request) {
        log.info("ApiController:  aggiungi domanda full");
        DomandaResponse resource = domandeService.addDomandaFull(request);
        if (resource.getReturnCode()==Response.OK) {
        	return ResponseEntity.ok(resource);
        } else  {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
		}
    }
	
	@PostMapping(path = CapLinks.SEARCH_CAP)
	public ResponseEntity<?> getCapList(@RequestBody Integer id){
		//Lista di cap da restituire
		List<String> listaCap = new ArrayList<String>();
		//Per recuperare la lista cap abbiamo bisogno del comune di cui ci è dato l'id
		Optional<Comuni> comune = comuniService.getComune(id);
		
		for(Cap cap : comune.get().getListaCap() ) {
			listaCap.add(cap.getCap());
		}
		
		CapResponse resource = new CapResponse();
		resource.setListaCap(listaCap);
		
		return ResponseEntity.ok(resource);
	}
	
	
	@GetMapping(path = DomandaLinks.GENERA_PROTOCOLLO)
	public ResponseEntity<?> getNewProtocolloDomanda(){
		ProtocolloDomandaResponse resource = domandeService.generaProtocollo();
		 if (resource.getReturnCode()==Response.OK) {
	        	return ResponseEntity.ok(resource);
	        } else  {
	        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Errore imprevisto, contattare l'assistenza");
			}
	}
	
	@PostMapping(path = ContrattoLinks.GET_CONTRATTO_BY_PROTOCOLLO)
	public ResponseEntity<?> getContrattoByProtocollo(@RequestBody String numProtocollo){
		
		ContrattoSearchResponse resource = contrattiService.getContrattoByProtocollo(numProtocollo);
        if (resource.getReturnCode()==Response.OK) {
        	return ResponseEntity.ok(resource);
        } else  {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
		}
	}
	
	@GetMapping(path = DomandaLinks.STAMPA_DOMANDA)
    public ResponseEntity<Resource> generaPdfDomanda(@PathVariable Long idDomanda) {
        try {
            byte[] pdfDomanda = domandeService.generaPdfDomanda(idDomanda);
            
            ByteArrayResource resource = new ByteArrayResource(pdfDomanda);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=domanda_report.pdf")
                    .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .contentLength(pdfDomanda.length)
                    .body(resource);
        } catch (IOException | TemplateException | DocumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
	    return user;
	  }
	  
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());	
			Authentication authentication = authenticationManager.authenticate(token);
			System.out.print("Stampa di prova\n" + authentication.toString() + "\n****************************" );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = Jwts.builder()
	                .setSubject(authRequest.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
	                .signWith(signingKey)
	                .compact();

        return ResponseEntity.ok(new AuthResponse(jwt));
		 
		}catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	    }
	}
	
}
