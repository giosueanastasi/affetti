package it.pittysoft.affetti.model;


import java.util.Date;

import it.pittysoft.affetti.entity.Domande;
import lombok.Data;

@Data
public class DomandaRequestSearch extends Request {
	
	
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Date dataProtocolloIniziale;
	private Date dataProtocolloFinale;
	private String numeroProtocollo;
	private String tipologia;
	private String stato;
}
