package it.pittysoft.affetti.model;


import java.util.Date;

import it.pittysoft.affetti.entity.Domande;
import lombok.Data;

@Data
public class DomandaRequest extends Request {
	
	private Domande domanda; 
	
	private String nomeAss;
	private String cognomeAss;
	private String comuneAss;
	private String provAss;
	private String dataDecesso;
	

}
