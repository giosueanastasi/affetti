package it.pittysoft.affetti.model;


import java.util.Date;

import lombok.Data;

@Data
public class ContrattoModel {

	private String numeroProtocolloContratto;
	private String stato;
	private Date dataProtocolloContratto;
	private Date dataScadenzaContratto;
	private String contraente;
	private String assegnatario;

}
