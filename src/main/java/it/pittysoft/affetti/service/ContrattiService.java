package it.pittysoft.affetti.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import it.pittysoft.affetti.config.FreeMarkerConfig;
import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.ContraentiModel;
import it.pittysoft.affetti.model.ContrattoModel;
import it.pittysoft.affetti.model.ContrattoResponse;
import it.pittysoft.affetti.model.ContrattoSearchRequest;
import it.pittysoft.affetti.model.ContrattoSearchResponse;
import it.pittysoft.affetti.model.PostiModel;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.repository.AssegnatariRepository;
import it.pittysoft.affetti.repository.ContraentiRepository;
import it.pittysoft.affetti.repository.ContrattiRepository;
import it.pittysoft.affetti.repository.DomandeRepository;
import it.pittysoft.affetti.repository.PostiRepository;
import lombok.Data;



@Component
public class ContrattiService {
	
	@Autowired
    Configuration freemarkerConfig;

	private ContrattiRepository contrattiRepository;

	private DomandeRepository domandaRepository;

	private ContraentiRepository contraentiRepository;

	private AssegnatariRepository assegnatariRepository;

	private PostiRepository postiRepository;

	
	
	public ContrattiService(ContrattiRepository contrattiRepository, DomandeRepository domandaRepository,
			ContraentiRepository contraentiRepository, AssegnatariRepository assegnatariRepository,
			PostiRepository postiRepository) {
		super();
		this.contrattiRepository = contrattiRepository;
		this.domandaRepository = domandaRepository;
		this.contraentiRepository = contraentiRepository;
		this.assegnatariRepository = assegnatariRepository;
		this.postiRepository = postiRepository;
	}

	public List<Contratti> getContratti() {
		return contrattiRepository.findAll();
	}

	public Contratti saveContratto(Contratti contratti) {
		return contrattiRepository.save(contratti);
	}

	public ContrattoResponse saveContratto(ContrattoModel contrattiRequest) {
		ContrattoResponse response = new ContrattoResponse();
	    Contratti contratti = new Contratti();
	    boolean isNewContratto = false;

	    if (contrattiRequest.getIdContratto() != null) {
	        contratti = contrattiRepository.findById(contrattiRequest.getIdContratto());
	        if (contratti == null) {
	            isNewContratto = true;
	        }
	    } else {
	        isNewContratto = true;
	    }

	    if (isNewContratto) {
	    	contratti = new Contratti();
	        contratti.setData_inizio(contrattiRequest.getDataProtocolloContratto());
	        contratti.setData_scadenza(contrattiRequest.getDataScadenzaContratto());
	        contratti.setStato(contrattiRequest.getStato());
		    
		    if (contrattiRequest.getIdDomanda() != null) {
		    	contratti.setDomanda(domandaRepository.findById(contrattiRequest.getIdDomanda()));
		    }
	    }

	    contratti.setProtocollo(contrattiRequest.getNumeroProtocolloContratto());
//		contratti.setId(contrattiRequest.getIdContratto());	
//    	contratti.setStato(contrattiRequest.getStato());
//    	contratti.setData_inizio(contrattiRequest.getDataProtocolloContratto());
//    	contratti.setData_scadenza(contrattiRequest.getDataProtocolloContratto());

		contrattiRepository.save(contratti);
        contrattiRequest.setIdContratto(contratti.getId());

//    	ContrattoModel contrattiModel = new ContrattoModel();
//    	contrattiModel.setIdContratto(contrattiSaved.getId());
//    	contrattiModel.setStato(contrattiSaved.getStato());
//    	contrattiModel.setNumeroProtocolloContratto(contrattiSaved.getProtocollo());
//    	contrattiModel.setDataProtocolloContratto(contrattiSaved.getData_inizio());
//    	contrattiModel.setDataScadenzaContratto(contrattiSaved.getData_scadenza());
//    	
//    	
//    	Domande domanda = domandaRepository.findById(contrattiRequest.getIdDomanda());
//    	contrattiModel.setIdDomanda(domanda.getId());
//    	contrattiModel.setProtocolloDomanda(domanda.getProtocollo());
//    	contrattiModel.setDataProtocolloDomanda(domanda.getData_protocollo());
//    	
//    	Posti posto = postiRepository.findById(domanda.getPosto().getId());
//    	contrattiModel.setIdPosto(domanda.getId());
//    	contrattiModel.setLoculo(posto.getLoculo());
//    	contrattiModel.setFornice(posto.getFornice()); 
//   	
//    	Contraenti contraente = contraentiRepository.findById(domanda.getContraente().getId());
//    	contrattiModel.setIdContraente(domanda.getId());
//    	contrattiModel.setNomeC(contraente.getNome());
//    	contrattiModel.setCognomeC(contraente.getCognome());
//    	contrattiModel.setCap_residenza(contraente.getCap_residenza());
//    	contrattiModel.setCivico_residenza(contraente.getCivico_residenza());
//    	contrattiModel.setCodice_fiscale(contraente.getCodice_fiscale());
//    	contrattiModel.setComune_nascita(contraente.getComune_nascita());
//    	contrattiModel.setComune_residenza(contraente.getComune_residenza());
//    	contrattiModel.setEmail(contraente.getEmail());
//    	contrattiModel.setNote(contraente.getNote());
//    	contrattiModel.setProvincia_nascita(contraente.getProvincia_nascita());
//    	contrattiModel.setProvincia_residenza(contraente.getProvincia_residenza());
//    	contrattiModel.setStato_nascita(contraente.getStato_nascita());
//    	contrattiModel.setTelefono(contraente.getTelefono());
//    	contrattiModel.setVia_residenza(contraente.getVia_residenza());
//    	contrattiModel.setData_nascita(contraente.getData_nascita());
//    	
//    	
//    	Assegnatari assegnatari = assegnatariRepository.findById(domanda.getAssegnatario().getId());
//    	contrattiModel.setIdAssegnatario(domanda.getId());
//    	contrattiModel.setData_decesso(assegnatari.getData_decesso());
//    	contrattiModel.setComune_decesso(assegnatari.getComune_decesso()); 
//    	contrattiModel.setNomeA(assegnatari.getNome());
//    	contrattiModel.setCognomeA(assegnatari.getCognome());
		

		response.getContratti().add(contrattiRequest);

		return response;
	}

	public ContrattoSearchResponse getContratti(ContrattoSearchRequest resquestSearch, Pageable pageable) {
		List<Contratti> findtContrattiByNomeAndCognome = contrattiRepository
				.findtContrattiByNomeAndCognome(resquestSearch);
		ContrattoSearchResponse response = new ContrattoSearchResponse();
		
		 //Lista di contratti model che verrà preparata ed usata per impostare l'oggetto di tipo page della response
		 List<ContrattoModel> listaContratti = new ArrayList<>();

		for (Contratti contratti : findtContrattiByNomeAndCognome) {
			ContrattoModel cm = new ContrattoModel(contratti);
			
			 cm.setIdContratto(contratti.getId());
			 cm.setNumeroProtocolloContratto(contratti.getProtocollo());
			 cm.setDataProtocolloContratto(contratti.getData_inizio());
			 cm.setDataScadenzaContratto(contratti.getData_scadenza());
			 cm.setStato(contratti.getStato());
			 
			listaContratti.add(cm);

		}
		
		//Impostiamo l'oggetto di tipo page della response
		final int start = (int)pageable.getOffset();
	    final int end = Math.min((start + pageable.getPageSize()), listaContratti.size());
	    final Page<ContrattoModel> page = new PageImpl<>(listaContratti.subList(start, end), pageable, listaContratti.size());
		
	    response.setContratti(page);
	    
		return response;
	}
	
public ContrattoResponse getContrattoByProtocollo(String numProtocollo) {
		
	ContrattoResponse response = new ContrattoResponse();
	Contratti contratto = contrattiRepository.findByProtocollo(numProtocollo);
	ContrattoModel cm = new ContrattoModel(contratto);
	
	response.getContratti().add(cm);
	
	return response;

	
	}

public  byte[] generaPdfContratti(Long idContratto) throws IOException, TemplateException, DocumentException {
	
	Contratti contratto = contrattiRepository.findById(idContratto);
	Map<String, Object> dati = new HashMap<>();
	dati.put("protocollo",contratto.getProtocollo());
	Map<String,String> contrattoMap = new HashMap<>();
	contrattoMap.put("dataInizio",contratto.getData_inizio().toString()); 
	contrattoMap.put("dataFine",contratto.getData_scadenza().toString()); 
	contrattoMap.put("nomeContraente",contratto.getDomanda().getContraente().getNome());
	contrattoMap.put("cognomeContraente",contratto.getDomanda().getContraente().getCognome());
	contrattoMap.put("comuneNascita",contratto.getDomanda().getContraente().getComune_nascita());
	contrattoMap.put("provinciaNascita",contratto.getDomanda().getContraente().getProvincia_nascita());
	contrattoMap.put("statoNascita",contratto.getDomanda().getContraente().getStato_nascita());
	contrattoMap.put("dataNascita",contratto.getDomanda().getContraente().getData_nascita().toString());
	contrattoMap.put("comuneResidenza",contratto.getDomanda().getContraente().getComune_residenza());
	contrattoMap.put("provinciaResidenza",contratto.getDomanda().getContraente().getProvincia_residenza());
	contrattoMap.put("viaResidenza",contratto.getDomanda().getContraente().getVia_residenza());
	contrattoMap.put("capResidenza",contratto.getDomanda().getContraente().getCap_residenza());
	contrattoMap.put("codiceFiscale",contratto.getDomanda().getContraente().getCodice_fiscale());
	contrattoMap.put("email",contratto.getDomanda().getContraente().getEmail());
	contrattoMap.put("telefono",contratto.getDomanda().getContraente().getTelefono());
	contrattoMap.put("note",contratto.getDomanda().getContraente().getNote());
	contrattoMap.put("loculo",contratto.getDomanda().getPosto().getLoculo());
	contrattoMap.put("fornice",contratto.getDomanda().getPosto().getFornice());
	contrattoMap.put("nomeAssegnatario",contratto.getDomanda().getAssegnatario().getNome());
	contrattoMap.put("cognomeAssegnatario",contratto.getDomanda().getAssegnatario().getCognome());
	contrattoMap.put("comuneDecesso",contratto.getDomanda().getAssegnatario().getComune_decesso());
	contrattoMap.put("dataDecesso",contratto.getDomanda().getAssegnatario().getData_decesso().toString());
	contrattoMap.put("protocolloDomanda",contratto.getDomanda().getProtocollo());
	contrattoMap.put("dataDomanda",contratto.getDomanda().getData_protocollo().toString());

	 dati.put("dati", contrattoMap);
	 
	
    // Carica il template
    Template template = freemarkerConfig.getTemplate("/contratto/contratto.ftl");

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
