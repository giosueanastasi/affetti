package it.pittysoft.affetti.model;


import java.util.Date;

import it.pittysoft.affetti.entity.Domande;
import lombok.Data;

@Data
public class DomandaModel {

	private Date dataProtocollo;
	private String numeroProtocolloDomanda;
	private String tipologia;
	private String stato;
	private String nomeContraente;
	private String cognomeContraente;
	private String Assegnatario;
	private String nomeAss;
	private String cognomeAss;
	private String numeroProtocolloContratto;
	private String comuneDiNascita;
	private String provinciaDiNascita;
	private String statoDiNascita;
	private String comuneDiResidenza;
	private String provinciaDiResidenza;
	private String viaDiResidenza;
	private String civicoDiResidenza;
	private String capDiResidenza;
	private String codiceFiscale;
	private String telefono;
	private String Email;
	private String note;
	private String loculo;
	private String fornice;
	private String comuneDecesso;
	private Date dataDecesso;
	
}
