package it.pittysoft.affetti.model;

import java.math.BigDecimal;
import java.util.Date;

import it.pittysoft.affetti.entity.Aree;
import it.pittysoft.affetti.entity.Cimiteri;
import lombok.Data;

@Data
public class PostiModel {
	private Long idDomanda;
	private Long id;
	private String nome;
	private String cognome;
	private String loculo;
	private String fornice;
	private String tipo;
	private Long fkArea;
	private Aree area;
	private Date scadenza;
	private String stato;
	private String protocolloContratto;
	private Boolean checked = false;
	private BigDecimal latitudine;
	private BigDecimal longitudine;
}
