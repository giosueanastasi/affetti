package it.pittysoft.affetti.model;



import java.util.Date;

import lombok.Data;

@Data
public class PostiRequest extends Request{
	private Long idDomanda;
	private Long id;
	private String nome;
	private String cognome;
	private String loculo;
	private String fornice;
	private Date scadenza;
	private String stato;
	private Date data_inizio;
	private Date data_scadenza;
	
	
}
