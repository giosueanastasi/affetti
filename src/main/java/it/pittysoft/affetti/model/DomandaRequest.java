package it.pittysoft.affetti.model;


import java.util.Date;

import it.pittysoft.affetti.entity.Domande;
import lombok.Data;

@Data
public class DomandaRequest extends Request {
	
	private Domande domanda; 
	
	private Long idAssegnatario;
	private Long idContraente;
	private Long idDomanda;
	private Long idPosto;

	private Date dataProtocollo;
	private String numeroProtocolloDomanda;
	private String tipologia;
	private String stato;
	private String nomeContraente;
	private String cognomeContraente;
	private String nomeAss;
	private String cognomeAss;
	private String comuneAss;
	private String provAss;

	private String comuneDecesso;
	private Date dataDecesso;
	
	private String loculo;
	private String fornice;
	private String fkContraente;
	
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
	private String email;
	private String note;

}
