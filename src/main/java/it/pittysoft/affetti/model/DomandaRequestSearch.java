package it.pittysoft.affetti.model;


import java.util.Date;

import it.pittysoft.affetti.entity.Domande;
import lombok.Data;

@Data
public class DomandaRequestSearch extends Request {
	
	private Domande domanda; 
	
	private String nomeC;
	private String cognomeC;
	private String codice_fiscaleC;
	private String data_protocollo_inizialeC;//non presenti in contraente
	private String data_protocollo_finaleC;//non presenti in contraente
	private String numero_protocolloC;
	private String statoC;
}
