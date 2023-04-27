package it.pittysoft.affetti.model;


import java.util.Date;

import it.pittysoft.affetti.entity.Contratti;
import lombok.Data;

@Data
public class ContrattoSearchRequest extends Request {
	
	private Contratti contratto; 
	
	private String nomeA;
	private String cognomeA;
	private String nomeC;
	private String cognomeC;
	private String codice_fiscaleC;
	private String data_protocollo_inizialeC;//non presenti in contraente
	private String data_protocollo_finaleC;//non presenti in contraente
	private String protocollo;
	private String stato;
	private String data_inizio;
	private String data_scadenza;
}
