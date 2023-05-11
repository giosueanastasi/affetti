package it.pittysoft.affetti.model;


import java.util.Date;

import lombok.Data;

@Data
public class ContrattoModel {

	private String numeroProtocolloContratto;
	private String stato;
	private Data dataProtocolloContratto;
	private Data dataScadenzaContratto;
	private String contraente;
	private String assegnatario;
	
	
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
	
	private String loculo;
	private String fornice;
	
	private String comune_decesso;
	private Date data_decesso;
	
	private String protocolloDomanda;
	private String dataProtocolloDomanda;
}
