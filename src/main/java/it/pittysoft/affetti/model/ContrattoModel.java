package it.pittysoft.affetti.model;


import java.util.Date;

import it.pittysoft.affetti.entity.Contratti;
import lombok.Data;

@Data
public class ContrattoModel {

	private Long idContratto;
	private String numeroProtocolloContratto;
	private String stato;
	private Date dataProtocolloContratto;
	private Date dataScadenzaContratto;
	private String contraente;
	private String assegnatario;
	
	private Long idContraente;
	private String nomeA;
	private String nomeC;
	private String cognomeA;
	private String cognomeC;
	private String codice_fiscale;
	private String comune_nascita;
	private String provincia_nascita;
	private String stato_nascita;
	private String data_nascita;
	private String comune_residenza;
	private String provincia_residenza;
	private String via_residenza;
	private String civico_residenza;
	private String cap_residenza;
	private String email;
	private String note;
	private String telefono;
	
	private Long idPosto;
	private String loculo;
	private String fornice;
	
	private Long idAssegnatario;
	private String comune_decesso;
	private Date data_decesso;
	
	private Long idDomanda;
	private String protocolloDomanda;
	private Date dataProtocolloDomanda;
	
	public ContrattoModel(){
		
	}
	
	//Overload del costruttore per accettare un'entity di tipo contratto ed acquisirne i campi
	public ContrattoModel(Contratti contratto){
		numeroProtocolloContratto = contratto.getProtocollo();
		stato = contratto.getStato();
		dataProtocolloContratto = contratto.getData_inizio();
		dataScadenzaContratto  = contratto.getData_scadenza();
		contraente = contratto.getDomanda().getContraente().getCognome() + " "
				+ contratto.getDomanda().getContraente().getNome();
		assegnatario  = contratto.getDomanda().getAssegnatario().getCognome() + " "
				+ contratto.getDomanda().getAssegnatario().getNome();
		codice_fiscale  = contratto.getDomanda().getContraente().getCodice_fiscale();
		comune_nascita  = contratto.getDomanda().getContraente().getComune_nascita();
		provincia_nascita  = contratto.getDomanda().getContraente().getProvincia_nascita();
		comune_nascita  = contratto.getDomanda().getContraente().getComune_nascita();
		stato_nascita  = contratto.getDomanda().getContraente().getStato_nascita();
		data_nascita  = contratto.getDomanda().getContraente().getData_nascita();
		comune_residenza = contratto.getDomanda().getContraente().getComune_residenza();
		provincia_residenza = contratto.getDomanda().getContraente().getProvincia_residenza();
		via_residenza = contratto.getDomanda().getContraente().getVia_residenza();
		civico_residenza = contratto.getDomanda().getContraente().getCivico_residenza();
		cap_residenza = contratto.getDomanda().getContraente().getCap_residenza();
		email = contratto.getDomanda().getContraente().getEmail();
		note = contratto.getDomanda().getContraente().getNote();
		telefono = contratto.getDomanda().getContraente().getTelefono();
		loculo = contratto.getDomanda().getPosto().getLoculo();
		fornice = contratto.getDomanda().getPosto().getFornice();
		comune_decesso = contratto.getDomanda().getAssegnatario().getComune_decesso();
		data_decesso = contratto.getDomanda().getAssegnatario().getData_decesso();
		protocolloDomanda = contratto.getDomanda().getProtocollo();
		dataProtocolloDomanda = contratto.getDomanda().getData_protocollo();
		cognomeC = contratto.getDomanda().getContraente().getCognome();
		nomeC = contratto.getDomanda().getContraente().getNome();
		nomeA = contratto.getDomanda().getAssegnatario().getNome();
		cognomeA = contratto.getDomanda().getAssegnatario().getCognome();
		idPosto = contratto.getDomanda().getPosto().getId();
		idAssegnatario = contratto.getDomanda().getAssegnatario().getId();
		idContraente = contratto.getDomanda().getContraente().getId();
		idContratto = contratto.getId();
	}
	
	
}
