package it.pittysoft.affetti.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.spec.SecretKeySpec;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.lowagie.text.DocumentException;

import freemarker.template.TemplateException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import freemarker.template.TemplateException;	
import it.pittysoft.affetti.entity.Comuni;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.links.ComuneLinks;
import it.pittysoft.affetti.links.ContraenteLinks;
import it.pittysoft.affetti.dto.DomandeDto;
import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Cap;
import it.pittysoft.affetti.entity.Comuni;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Defunti;
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
import it.pittysoft.affetti.model.ContrattoSearchRequest;
import it.pittysoft.affetti.model.ContrattoSearchResponse;
import it.pittysoft.affetti.model.DefuntiRequest;
import it.pittysoft.affetti.model.DomandaModel;
import it.pittysoft.affetti.model.ProtocolloDomandaResponse;
import it.pittysoft.affetti.model.CapResponse;
import it.pittysoft.affetti.model.ComuniSelectResponse;
import it.pittysoft.affetti.model.ContraentiRequest;
import it.pittysoft.affetti.model.ContraentiResponse;
import it.pittysoft.affetti.model.ContrattoModel;
import it.pittysoft.affetti.model.ContrattoSearchRequest;
import it.pittysoft.affetti.model.ContrattoSearchResponse;
import it.pittysoft.affetti.model.ContrattoResponse;
import it.pittysoft.affetti.model.DomandaRequest;
import it.pittysoft.affetti.model.DomandaRequestSearch;
import it.pittysoft.affetti.model.DomandaResponse;
import it.pittysoft.affetti.model.DomandaResponseSearch;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.model.PostiSearchResponse;
import it.pittysoft.affetti.model.Response;
import it.pittysoft.affetti.model.UserRequest;
import it.pittysoft.affetti.model.UserResponse;
import it.pittysoft.affetti.model.ProtocolloDomandaResponse;
import it.pittysoft.affetti.model.Response;
import it.pittysoft.affetti.model.UserRequest;
import it.pittysoft.affetti.model.UserResponse;
import it.pittysoft.affetti.entity.Role;
import it.pittysoft.affetti.model.ChangeEmailRequest;
import it.pittysoft.affetti.model.ChangePasswordRequest;
import it.pittysoft.affetti.model.ProfileResponse;
import it.pittysoft.affetti.model.RegisterRequest;
import it.pittysoft.affetti.repository.RoleRepository;
import it.pittysoft.affetti.security.AuthRequest;
import it.pittysoft.affetti.security.AuthResponse;
import it.pittysoft.affetti.service.AssegnatariService;
import it.pittysoft.affetti.model.PostiSearchResponse;
import it.pittysoft.affetti.model.Response;
import it.pittysoft.affetti.model.UserRequest;
import it.pittysoft.affetti.model.UserResponse;
import it.pittysoft.affetti.repository.DefuntiRepository;
import it.pittysoft.affetti.service.ComuniService;
import it.pittysoft.affetti.service.ContraentiService;
import it.pittysoft.affetti.service.ContrattiService;
import it.pittysoft.affetti.service.DefuntiService;
import it.pittysoft.affetti.service.DomandeService;
import it.pittysoft.affetti.service.PostiService;
import it.pittysoft.affetti.service.UsersService;
import it.pittysoft.affetti.service.ContrattiService;
import it.pittysoft.affetti.service.DefuntiService;
import it.pittysoft.affetti.service.DomandeService;
import it.pittysoft.affetti.service.AssegnatariService;
import it.pittysoft.affetti.service.TenantService;
import it.pittysoft.affetti.entity.Cimiteri;
import it.pittysoft.affetti.entity.Tenant;
import it.pittysoft.affetti.links.CimiteroLinks;
import it.pittysoft.affetti.links.DefuntoLinks;
import it.pittysoft.affetti.links.TenantLinks;
import it.pittysoft.affetti.service.CimiteriService;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;
    
	
    @Value("${jwt.secret}")
    String jwtSecret;

	@Autowired
	DefuntiService defuntiService;

	@Autowired
	TenantService tenantService;

	@Autowired
	CimiteriService cimiteriService;

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
        ContrattoResponse resource = contrattiService.saveContratto(contratto);
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
    public ResponseEntity<?> searchDomande(@RequestBody DomandaRequestSearch resquestSearch,
    		@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
        log.info("ApiController:  search domande");
        DomandaResponseSearch resource = domandeService.getDomande(resquestSearch, pageable);
        if (resource.getReturnCode()==Response.OK) {
        	return ResponseEntity.ok(resource);
        } else  {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
        }
	}
	
	@PostMapping(path = PostoLinks.SEARCH_POSTI)
    public ResponseEntity<?> searchPosti(@RequestBody PostiRequest posti,
    		@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
		
        log.info("ApiController:  search posti");
        PostiSearchResponse resource = postiService.getPosti(posti, pageable);
        if (resource.getReturnCode()==Response.OK) {
        	return ResponseEntity.ok(resource);
        } else  {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
		}
	}
	
	@PostMapping(path = ContrattoLinks.SEARCH_CONTRATTO)
    public ResponseEntity<?> searchContratto(@RequestBody ContrattoSearchRequest contratti,
    		@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
        log.info("ApiController:  search contratti");
        ContrattoSearchResponse resource = contrattiService.getContratti(contratti, pageable);
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
	
	@GetMapping(path = DomandaLinks.GET_DOMANDA)
	public ResponseEntity<?> getDomandaById(@PathVariable Long id) {
		log.info("ApiController: get domanda {}", id);
		DomandaModel dm = domandeService.getDomandaModelById(id);
		if (dm != null) {
			return ResponseEntity.ok(dm);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Domanda non trovata");
		}
	}

	@PostMapping(path = DomandaLinks.ADD_DOMANDA)
	public ResponseEntity<?> saveDomanda(@RequestBody Domande domanda) {
        log.info("ApiController:  list domande");
        Domande savedDomanda = domandeService.saveDomanda(domanda);
        
        DomandeDto dto = domandeService.convertToDto(savedDomanda);
        return ResponseEntity.ok(dto);
    }
	
    /*
     * 	@PostMapping(path = PostoLinks.ADD_POSTO)
	public ResponseEntity<?> savePosto(@RequestBody PostiRequest posto) {
    log.info("ApiController:  list posti");
    PostiResponse resource = postiService.savePosto(posto);
    return ResponseEntity.ok(resource);
     */
	
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
		
		ContrattoResponse resource = contrattiService.getContrattoByProtocollo(numProtocollo);
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

	@GetMapping(path = UserLinks.PROFILE)
	public ResponseEntity<?> getProfile() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Users> userOpt = usersService.findByUsername(username);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato");
		}
		Users user = userOpt.get();
		ProfileResponse response = new ProfileResponse();
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		List<String> roles = new ArrayList<>();
		for (Role role : user.getRoles()) {
			roles.add(role.getRole());
		}
		response.setRoles(roles);
		return ResponseEntity.ok(response);
	}

	@PutMapping(path = UserLinks.PROFILE_PASSWORD)
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Users> userOpt = usersService.findByUsername(username);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato");
		}
		Users user = userOpt.get();
		if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password attuale non corretta");
		}
		if (request.getNewPassword() == null || request.getNewPassword().length() < 8) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La nuova password deve essere di almeno 8 caratteri");
		}
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		usersService.saveUser(user);
		return ResponseEntity.ok("Password aggiornata con successo");
	}

	@PutMapping(path = UserLinks.PROFILE_EMAIL)
	public ResponseEntity<?> changeEmail(@RequestBody ChangeEmailRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Users> userOpt = usersService.findByUsername(username);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato");
		}
		Users user = userOpt.get();
		user.setEmail(request.getEmail());
		usersService.saveUser(user);
		return ResponseEntity.ok("Email aggiornata con successo");
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		if (request.getUsername() == null || request.getUsername().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username obbligatorio");
		}
		if (request.getPassword() == null || request.getPassword().length() < 8) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La password deve essere di almeno 8 caratteri");
		}
		Optional<Users> existing = usersService.findByUsername(request.getUsername());
		if (existing.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username gia' in uso");
		}
		Optional<Role> userRole = roleRepository.findByRole("user");
		if (userRole.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore di configurazione ruoli");
		}
		Users newUser = new Users();
		newUser.setUsername(request.getUsername());
		newUser.setPassword(passwordEncoder.encode(request.getPassword()));
		newUser.setEmail(request.getEmail());
		newUser.setFk_comune(String.valueOf(request.getFkComune()));
		newUser.setRoles(java.util.Set.of(userRole.get()));
		usersService.saveUser(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body("Registrazione completata con successo");
	}

	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
			Authentication authentication = authenticationManager.authenticate(token);
			List<String> roles = new ArrayList<String>();
			for(GrantedAuthority auth : authentication.getAuthorities()) {
				roles.add(auth.getAuthority().toString());
			}
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Fetch tenant info for the JWT
	        Optional<Users> userOpt = usersService.findByUsername(authRequest.getUsername());
	        io.jsonwebtoken.JwtBuilder builder = Jwts.builder()
	                .setSubject(authRequest.getUsername())
	                .claim("roles", roles);
	        if (userOpt.isPresent() && userOpt.get().getTenant() != null) {
	            builder.claim("tenantId", userOpt.get().getTenant().getId());
	            builder.claim("tenantDescrizione", userOpt.get().getTenant().getDescrizione());
	        }
	        Key signingKey = new SecretKeySpec(jwtSecret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
	        String jwt = builder
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
	                .signWith(signingKey, SignatureAlgorithm.HS256)
	                .compact();

        return ResponseEntity.ok(new AuthResponse(jwt));

		}catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	    }
	}


	@PostMapping(path = DefuntoLinks.SEARCH_DEFUNTI)
	public ResponseEntity<List<Defunti>> ricercaDefunti(@RequestBody DefuntiRequest request) {
	    System.out.println("Ricerca ricevuta: " + request);
		List<Defunti> defuntiFiltrati = defuntiService.getDefunti(request);

		return ResponseEntity.ok(defuntiFiltrati);
	}

	@GetMapping(path = DefuntoLinks.SEARCH_DEFUNTO)
	public ResponseEntity<?> getDefuntoById(@PathVariable Long id){
		Optional<Defunti> defuntoOptional = defuntiService.getDefuntiById(id);

		if(defuntoOptional.isPresent()) {
			return ResponseEntity.ok(defuntoOptional.get());
		} else {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Errore imprevisto, contattare l'assistenza");
		}
	}

	@GetMapping(path = ContrattoLinks.STAMPA_CONTRATTO)
    public ResponseEntity<Resource> generaPdfContratto(@PathVariable Long idContratto) {
        try {
            byte[] pdfContratto = contrattiService.generaPdfContratti(idContratto);

            ByteArrayResource resource = new ByteArrayResource(pdfContratto);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=contratto_report.pdf")
                    .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .contentLength(pdfContratto.length)
                    .body(resource);
        } catch (IOException | TemplateException | DocumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

	// ==================== TENANT ENDPOINTS ====================

	@PostMapping(path = TenantLinks.ADD_TENANT)
	public ResponseEntity<?> createTenant(@RequestBody Tenant tenant, Authentication authentication) {
		log.info("ApiController: create tenant");
		boolean isSuperadmin = authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_SUPERADMIN"));
		if (!isSuperadmin) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo SUPERADMIN puo' creare tenant");
		}
		Tenant saved = tenantService.saveTenant(tenant);
		return ResponseEntity.ok(saved);
	}

	@PutMapping(path = TenantLinks.UPDATE_TENANT)
	public ResponseEntity<?> updateTenant(@PathVariable Long id, @RequestBody Tenant tenant, Authentication authentication) {
		log.info("ApiController: update tenant {}", id);
		boolean isSuperadmin = authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_SUPERADMIN"));
		if (!isSuperadmin) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo SUPERADMIN puo' modificare tenant");
		}
		Tenant updated = tenantService.updateTenant(id, tenant);
		return ResponseEntity.ok(updated);
	}

	@org.springframework.web.bind.annotation.DeleteMapping(path = TenantLinks.DELETE_TENANT)
	public ResponseEntity<?> deleteTenant(@PathVariable Long id, Authentication authentication) {
		log.info("ApiController: delete tenant {}", id);
		boolean isSuperadmin = authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_SUPERADMIN"));
		if (!isSuperadmin) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo SUPERADMIN puo' eliminare tenant");
		}
		Optional<Tenant> tenant = tenantService.getTenantById(id);
		if (tenant.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tenant non trovato");
		}
		tenantService.deleteTenant(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping(path = TenantLinks.UPLOAD_TENANT_LOGO)
	public ResponseEntity<?> uploadTenantLogo(@PathVariable Long id,
			@RequestParam("file") org.springframework.web.multipart.MultipartFile file,
			Authentication authentication) {
		log.info("ApiController: upload logo for tenant {}", id);
		boolean isSuperadmin = authentication.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_SUPERADMIN"));
		if (!isSuperadmin) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo SUPERADMIN puo' caricare loghi");
		}
		Optional<Tenant> tenantOpt = tenantService.getTenantById(id);
		if (tenantOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tenant non trovato");
		}
		try {
			String logoUrl = tenantService.saveLogoFile(id, file);
			Tenant tenant = tenantOpt.get();
			tenant.setLogoUrl(logoUrl);
			tenantService.saveTenant(tenant);
			return ResponseEntity.ok(java.util.Map.of("logoUrl", logoUrl));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nel caricamento del logo");
		}
	}

	@GetMapping(path = TenantLinks.LIST_TENANTS)
	public ResponseEntity<?> listTenants() {
		log.info("ApiController: list tenants");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<Users> userOpt = usersService.findByUsername(username);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato");
		}
		Users user = userOpt.get();
		boolean isSuperadmin = user.getRoles().stream()
				.anyMatch(r -> r.getRole().equalsIgnoreCase("superadmin"));
		if (isSuperadmin) {
			return ResponseEntity.ok(tenantService.getAllTenants());
		} else if (user.getTenant() != null) {
			return ResponseEntity.ok(List.of(user.getTenant()));
		} else {
			return ResponseEntity.ok(List.of());
		}
	}

	@GetMapping(path = TenantLinks.GET_TENANT)
	public ResponseEntity<?> getTenant(@PathVariable Long id) {
		log.info("ApiController: get tenant {}", id);
		Optional<Tenant> tenant = tenantService.getTenantById(id);
		if (tenant.isPresent()) {
			return ResponseEntity.ok(tenant.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tenant non trovato");
		}
	}

	@GetMapping(path = TenantLinks.GET_TENANT_BY_SLUG)
	public ResponseEntity<?> getTenantBySlug(@PathVariable String slug) {
		log.info("ApiController: get tenant by slug {}", slug);
		Optional<Tenant> tenant = tenantService.getTenantBySlug(slug);
		if (tenant.isPresent()) {
			return ResponseEntity.ok(tenant.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tenant non trovato");
		}
	}

	@GetMapping(path = TenantLinks.TENANT_CIMITERI)
	public ResponseEntity<?> getTenantCimiteri(@PathVariable Long id) {
		log.info("ApiController: get cimiteri for tenant {}", id);
		List<Cimiteri> cimiteri = tenantService.getCimiteriByTenant(id);
		return ResponseEntity.ok(cimiteri);
	}

	@PostMapping(path = TenantLinks.TENANT_SEARCH_DEFUNTI)
	public ResponseEntity<?> searchDefuntiByTenant(@PathVariable Long id,
			@RequestParam(required = false) List<Long> cimiteroIds,
			@RequestBody DefuntiRequest request) {
		log.info("ApiController: search defunti for tenant {}", id);
		List<Defunti> defunti = tenantService.searchDefuntiByTenant(id, cimiteroIds, request);
		return ResponseEntity.ok(tenantService.enrichDefuntiCards(defunti));
	}

	@GetMapping(path = TenantLinks.TENANT_DEFUNTI)
	public ResponseEntity<?> getRecentDefuntiByTenant(@PathVariable Long id,
			@RequestParam(required = false) List<Long> cimiteroIds,
			@RequestParam(defaultValue = "20") int limit) {
		log.info("ApiController: get recent defunti for tenant {}", id);
		List<Defunti> defunti = tenantService.getRecentDefuntiByTenant(id, cimiteroIds, limit);
		return ResponseEntity.ok(tenantService.enrichDefuntiCards(defunti));
	}

	// ==================== CIMITERI ENDPOINTS ====================

	@PostMapping(path = CimiteroLinks.ADD_CIMITERO)
	public ResponseEntity<?> saveCimitero(@RequestBody Cimiteri cimitero) {
		log.info("ApiController: save cimitero");
		Cimiteri saved = cimiteriService.saveCimitero(cimitero);
		return ResponseEntity.ok(saved);
	}

	@GetMapping(path = CimiteroLinks.GET_CIMITERO)
	public ResponseEntity<?> getCimitero(@PathVariable Long id) {
		log.info("ApiController: get cimitero {}", id);
		Optional<Cimiteri> cimitero = cimiteriService.getCimiteroById(id);
		if (cimitero.isPresent()) {
			return ResponseEntity.ok(cimitero.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cimitero non trovato");
	}

	@org.springframework.web.bind.annotation.DeleteMapping(path = CimiteroLinks.DELETE_CIMITERO)
	public ResponseEntity<?> deleteCimitero(@PathVariable Long id) {
		log.info("ApiController: delete cimitero {}", id);
		Optional<Cimiteri> cimitero = cimiteriService.getCimiteroById(id);
		if (cimitero.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cimitero non trovato");
		}
		cimiteriService.deleteCimitero(id);
		return ResponseEntity.ok().build();
	}

}
