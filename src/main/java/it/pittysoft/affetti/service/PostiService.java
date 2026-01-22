package it.pittysoft.affetti.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import it.pittysoft.affetti.entity.Aree;
import it.pittysoft.affetti.entity.Cimiteri;
import it.pittysoft.affetti.entity.Contraenti;
import it.pittysoft.affetti.entity.Contratti;
import it.pittysoft.affetti.entity.Domande;
import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.entity.TipiSepoltura;
import it.pittysoft.affetti.model.ContraentiModel;
import it.pittysoft.affetti.model.PostiModel;
import it.pittysoft.affetti.model.PostiRequest;
import it.pittysoft.affetti.model.PostiResponse;
import it.pittysoft.affetti.model.PostiSearchResponse;
import it.pittysoft.affetti.repository.ContraentiRepository;
import it.pittysoft.affetti.repository.ContrattiRepository;
import it.pittysoft.affetti.repository.DomandeRepository;
import it.pittysoft.affetti.repository.PostiRepository;
import it.pittysoft.affetti.repository.TipiSepolturaRepository;



@Component
public class PostiService {
	
	private PostiRepository postiRepository;

	@Autowired
	private DomandeRepository domandaRepository;

	@Autowired
	private ContrattiRepository contrattiRepository;

	@Autowired
	private TipiSepolturaRepository tipiSepolturaRepository;

    public PostiService(PostiRepository postiRepository) {
        this.postiRepository = postiRepository;
    }

    public List<Posti> getPosti() {
        return postiRepository.findAll();
    }
    
    public PostiResponse savePosto(PostiRequest postiRequest) {
    	PostiResponse response = new PostiResponse();

    	Posti posti = new Posti();
    	posti.setId(postiRequest.getId());
    	posti.setLoculo(postiRequest.getLoculo());
    	posti.setFornice(postiRequest.getFornice());

    	// Imposta tipo sepoltura se fornito
    	TipiSepoltura tipoSepoltura = null;
    	if (postiRequest.getTipo() != null && !postiRequest.getTipo().isEmpty()) {
    		tipoSepoltura = tipiSepolturaRepository.findByCodice(postiRequest.getTipo());
    		posti.setTipoSepoltura(tipoSepoltura);
    	}

    	posti.setStato(postiRequest.getStato());
    	posti.setLatitudine(postiRequest.getLatitudine());
    	posti.setLongitudine(postiRequest.getLongitudine());

    	// Imposta area se fornita
    	Aree area = null;
    	if (postiRequest.getFkArea() != null) {
    		area = new Aree();
    		area.setId(postiRequest.getFkArea());
    		posti.setArea(area);
    	}

    	// Genera codice automatico se non presente
    	if (posti.getCodice() == null || posti.getCodice().isEmpty()) {
    		posti.setCodice(generateCodicePosto(area, tipoSepoltura, postiRequest.getFornice(), postiRequest.getLoculo()));
    	}

    	Posti postiSaved = postiRepository.save(posti);

    	PostiModel postiModel = new PostiModel();
    	postiModel.setId(postiSaved.getId());
    	postiModel.setLoculo(postiSaved.getLoculo());
    	postiModel.setFornice(postiSaved.getFornice());

    	// Imposta tipo come codice
    	if (postiSaved.getTipoSepoltura() != null) {
    		postiModel.setTipo(postiSaved.getTipoSepoltura().getCodice());
    	}

    	postiModel.setStato(postiSaved.getStato());
    	postiModel.setLatitudine(postiSaved.getLatitudine());
    	postiModel.setLongitudine(postiSaved.getLongitudine());
    	postiModel.setArea(postiSaved.getArea());
    	if (postiSaved.getArea() != null) {
    		postiModel.setFkArea(postiSaved.getArea().getId());
    	}

    	Domande domanda = domandaRepository.findById(postiRequest.getIdDomanda());
    	postiModel.setIdDomanda(domanda.getId());
    	postiModel.setCognome(domanda.getAssegnatario().getCognome());
    	postiModel.setNome(domanda.getAssegnatario().getNome());
   	
    	Contratti contratti = contrattiRepository.findByDomanda(domanda);
    	postiModel.setScadenza(contratti.getData_scadenza());
    	
    	   	
    	response.getPosti().add(postiModel);
    	
    	
    	return response;
    }
    
    public PostiSearchResponse getPosti(PostiRequest posti, Pageable pageable) {
		 List<Posti> findtPostiByLoculoAndFornice = postiRepository.findtPostiByLoculoAndFornice(posti);
		 PostiSearchResponse response = new PostiSearchResponse();
		 
		 //Lista di posti model che verrà preparata ed usata per impostare l'oggetto di tipo page della response
		 List<PostiModel> listaPosti = new ArrayList<>();
		 
		 for (Posti postiFiltrati : findtPostiByLoculoAndFornice) {

			 PostiModel pm = new PostiModel();
			 pm.setId(postiFiltrati.getId());
			 pm.setLoculo(postiFiltrati.getLoculo());
			 pm.setFornice(postiFiltrati.getFornice());

			 // Imposta tipo come codice
			 if (postiFiltrati.getTipoSepoltura() != null) {
			 	pm.setTipo(postiFiltrati.getTipoSepoltura().getCodice());
			 }

			 pm.setStato(postiFiltrati.getStato());
			 pm.setLatitudine(postiFiltrati.getLatitudine());
			 pm.setLongitudine(postiFiltrati.getLongitudine());
			 pm.setArea(postiFiltrati.getArea());
			 if (postiFiltrati.getArea() != null) {
			 	pm.setFkArea(postiFiltrati.getArea().getId());
			 }

			 if(!postiFiltrati.getDomande().isEmpty()) {
				 for (Domande domanda : postiFiltrati.getDomande()) {
					 pm.setIdDomanda(domanda.getId());
					 pm.setCognome(domanda.getAssegnatario().getCognome());
					 pm.setNome(domanda.getAssegnatario().getNome());
					 pm.setScadenza(domanda.getContratto().getData_scadenza());
					 pm.setProtocolloContratto(domanda.getContratto().getProtocollo());
				 }
			 }
			 
			  listaPosti.add(pm);
		 }
		 //Impostiamo l'oggetto di tipo page della response
		 final int start = (int)pageable.getOffset();
		 final int end = Math.min((start + pageable.getPageSize()), listaPosti.size());
	     final Page<PostiModel> page = new PageImpl<>(listaPosti.subList(start, end), pageable, listaPosti.size());
	     
	     response.setPosti(page);
	     
		return response;
	}

	/**
	 * Genera automaticamente il codice univoco per un posto.
	 * Formato: {codice_cimitero}-{codice_area}-{tipo_sepoltura}-{fornice}-{loculo}
	 * Esempio: CIM001-A-LOCULO-8-1
	 */
	private String generateCodicePosto(Aree area, TipiSepoltura tipoSepoltura, String fornice, String loculo) {
		StringBuilder codice = new StringBuilder();

		// Aggiungi codice cimitero e area
		if (area != null && area.getCimitero() != null) {
			codice.append(area.getCimitero().getCodice());
			codice.append("-");
			codice.append(area.getCodice());
		}

		// Aggiungi tipo sepoltura
		if (tipoSepoltura != null) {
			codice.append("-");
			codice.append(tipoSepoltura.getCodice());
		}

		// Aggiungi fornice e loculo
		if (fornice != null && !fornice.isEmpty()) {
			codice.append("-");
			codice.append(fornice);
		}
		if (loculo != null && !loculo.isEmpty()) {
			codice.append("-");
			codice.append(loculo);
		}

		return codice.toString();
	}

}