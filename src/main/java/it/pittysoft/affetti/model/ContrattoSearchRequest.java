package it.pittysoft.affetti.model;


import java.util.Date;

import it.pittysoft.affetti.entity.Contratti;
import lombok.Data;

@Data
public class ContrattoSearchRequest extends Request {
	
	private Long idContratto;
	private Long idDomanda;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Date dataProtocolloIniziale;
	private Date dataProtocolloFinale;
	private String numeroProtocollo;
	private String tipologia;
	private String stato;
	
}
