package it.pittysoft.affetti.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import it.pittysoft.affetti.dao.DomandaDao;
import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.DomandaModel;
import it.pittysoft.affetti.model.DomandaRequest;
import it.pittysoft.affetti.model.DomandaRequestSearch;
import it.pittysoft.affetti.model.DomandaResponse;
import it.pittysoft.affetti.model.DomandaResponseSearch;
import it.pittysoft.affetti.model.ProtocolloDomandaModel;
import it.pittysoft.affetti.model.ProtocolloDomandaResponse;
import it.pittysoft.affetti.repository.DomandeRepository;
import it.pittysoft.affetti.utils.Protocollo;



@Component
public class DomandeService {
	
	@Autowired
	private DomandeRepository domandeRepository;
	

	@Autowired
	private DomandaDao domandaDao;
	
	@Autowired
    Configuration freemarkerConfig;
	


	public List<Domande> getDomande() {
        return domandeRepository.findAll();
    }
    
    public Domande saveDomanda(Domande domande) {
    	return domandeRepository.save(domande);
    }
    

    
    public DomandaResponseSearch getDomande(DomandaRequestSearch resquestSearch) {
		 List<Domande> findDomandeByCognomeAndNome = domandaDao.findDomandeByCognomeAndNome(resquestSearch);
		 DomandaResponseSearch response = new DomandaResponseSearch();
		  
		 for (Domande domanda : findDomandeByCognomeAndNome) {
			 DomandaModel dm = new DomandaModel();
			 
			 dm.setId(domanda.getId());
			 dm.setFk_posto(domanda.getPosto().getId());
			 dm.setDataProtocollo(domanda.getData_protocollo());
			 dm.setNumeroProtocolloDomanda(domanda.getProtocollo());
			 dm.setTipologia(domanda.getTipologia());
			 dm.setStato(domanda.getStato());	
			 dm.setCognomeContraente(domanda.getContraente().getCognome());
			 dm.setNomeContraente(domanda.getContraente().getNome());
			 //dm.setAssegnatario(domanda.getContraente().getCognome()+" "+domanda.getAssegnatario().getNome());
			 //dm.setNumeroProtocolloContratto(domanda.getContratto().getProtocollo());
			 dm.setComuneDiNascita(domanda.getContraente().getComune_nascita());
			 dm.setProvinciaDiNascita(domanda.getContraente().getProvincia_nascita());
			 dm.setStatoDiNascita(domanda.getContraente().getStato_nascita());
			 dm.setComuneDiResidenza(domanda.getContraente().getComune_residenza());
			 dm.setProvinciaDiResidenza(domanda.getContraente().getProvincia_residenza());
			 dm.setViaDiResidenza(domanda.getContraente().getVia_residenza());
			 dm.setCivicoDiResidenza(domanda.getContraente().getCivico_residenza());
			 dm.setCapDiResidenza(domanda.getContraente().getCap_residenza());
			 dm.setCodiceFiscale(domanda.getContraente().getCodice_fiscale());
			 dm.setTelefono(domanda.getContraente().getTelefono());
			 dm.setEmail(domanda.getContraente().getEmail());
			 dm.setNote(domanda.getContraente().getNote());
			 dm.setLoculo(domanda.getPosto().getLoculo());
			 dm.setFornice(domanda.getPosto().getFornice());
			 dm.setComuneDecesso(domanda.getAssegnatario().getComune_decesso());
			 dm.setDataDecesso(domanda.getAssegnatario().getData_decesso());
			 dm.setNomeAss(domanda.getAssegnatario().getNome());
			 dm.setCognomeAss(domanda.getAssegnatario().getCognome());
			 dm.setContratto(domanda.getContratto());
			 dm.setDataNascita(domanda.getContraente().getData_nascita());
			 
			 response.getDomande().add(dm);
		 } 
 	return response;
		
	}

    
    
    public DomandaResponse addDomandaFull(DomandaRequest request) {
    	DomandaResponse response = new DomandaResponse();
    	Assegnatari assegnatario = new Assegnatari();
    	if(request.getCognomeAss()!=null &&
    			request.getNomeAss()!=null //TODO finire
    			) {
    		assegnatario.setCognome(request.getCognomeAss());
    		assegnatario.setNome(request.getNomeAss());
    		assegnatario.setComune_decesso(request.getComuneAss());
    		assegnatario.setData_decesso(request.getDataDecesso());
    		assegnatario.setFk_user_modifier("1");
    		assegnatario.setData_insert(null);//now
    		assegnatario.setData_update(null);
    		
    		Posti posto = new Posti();
    		posto.setLoculo(request.getLoculo());
    		posto.setFornice(request.getFornice());
    		
    		Contraenti contraente = new Contraenti();
    		contraente.setId(Long.parseLong(request.getFkContraente()));
    		
    		
    		domandaDao.addDomandaFull(request.getDomanda(), assegnatario, posto,contraente);
    		
    	}else {
    		//errore, mancano i dati dell'assegnatario
    		response.setReturnCode(2);
    		response.setReasonCode("Mancanza campi obbligatori assegnarario");
    	}
    	
    	
    	return response;

    }
    
    public ProtocolloDomandaResponse generaProtocollo() {
    	
    	ProtocolloDomandaModel protocolloDomanda = new ProtocolloDomandaModel();
    	
    	List<Domande> domande = domandeRepository.findAll();
    	
    	protocolloDomanda.setProtocollo(Protocollo.generaProtocolloDomanda(domande));
    	
    	ProtocolloDomandaResponse response = new ProtocolloDomandaResponse();
    	response.setProtocolloDomanda(protocolloDomanda);
    	
    	return response;
    }
    
    public  byte[] generaPdfDomanda(Long idDomanda) throws IOException, TemplateException, DocumentException {
    	
    	Domande domanda = domandeRepository.findById(idDomanda);
    	Map<String, Object> dati = new HashMap<>();
    	dati.put("protocollo",domanda.getProtocollo());
    	Map<String,String> domandaMap = new HashMap<>();
		 domandaMap.put("data",domanda.getData_protocollo().toString()); 
		 domandaMap.put("tipologia",domanda.getTipologia());
		 domandaMap.put("stato",domanda.getStato());	
		 domandaMap.put("cognomeContraente",domanda.getContraente().getCognome());
		 domandaMap.put("nomeContraente",domanda.getContraente().getNome());
		 domandaMap.put("comuneNascita",domanda.getContraente().getComune_nascita());
		 domandaMap.put("provinciaNascita",domanda.getContraente().getProvincia_nascita());
		 domandaMap.put("statoNascita",domanda.getContraente().getStato_nascita());
		 domandaMap.put("comuneResidenza",domanda.getContraente().getComune_residenza());
		 domandaMap.put("provinciaResidenza",domanda.getContraente().getProvincia_residenza());
		 domandaMap.put("viaResidenza",domanda.getContraente().getVia_residenza());
		 domandaMap.put("civicoResidenza",domanda.getContraente().getCivico_residenza());
		 domandaMap.put("capResidenza",domanda.getContraente().getCap_residenza());
		 domandaMap.put("codiceFiscale",domanda.getContraente().getCodice_fiscale());
		 domandaMap.put("telefono",domanda.getContraente().getTelefono());
		 domandaMap.put("email",domanda.getContraente().getEmail());
		 domandaMap.put("note",domanda.getContraente().getNote());
		 domandaMap.put("loculo",domanda.getPosto().getLoculo());
		 domandaMap.put("fornice",domanda.getPosto().getFornice());
		 domandaMap.put("comuneDecesso",domanda.getAssegnatario().getComune_decesso());
		 domandaMap.put("dataDecesso",domanda.getAssegnatario().getData_decesso().toString());
		 domandaMap.put("nomeAssegnatario",domanda.getAssegnatario().getNome());
		 domandaMap.put("cognomeAssegnatario",domanda.getAssegnatario().getCognome());
		 domandaMap.put("dataNascita",domanda.getContraente().getData_nascita());
		 dati.put("dati", domandaMap);
		 
    	
        // Carica il template
        Template template = freemarkerConfig.getTemplate("/domanda/domanda.ftl");

        // Genera HTML
        StringWriter writer = new StringWriter();
        template.process(dati, writer);
        String htmlContent = writer.toString();

    	 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         ITextRenderer renderer = new ITextRenderer();
         renderer.setDocumentFromString(htmlContent);
         renderer.layout();
         renderer.createPDF(outputStream);
        
         return outputStream.toByteArray();	
    }

}
