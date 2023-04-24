package it.pittysoft.affetti.model;

import lombok.Data;

@Data
public class ContraentiModel {
	private String nome;
	private String cognome;
	private String codice_fiscale;
	private String protocolloD;
	private String protocolloC;
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
	private String nomeAss;
	private String cognomeAss;

	private Boolean checked = false; 
}
