package it.pittysoft.affetti.model;


import java.util.Date;

import it.pittysoft.affetti.entity.Domande;
import lombok.Data;

@Data
public class DomandaModel {

	private String numeroProtocolloDomanda;
	private Date dataProtocollo;
	private String stato;
	private String nomeContraente;
	private String cognomeContraente;
	private String Assegnatario;
	private String numeroProtocolloContratto;
}
