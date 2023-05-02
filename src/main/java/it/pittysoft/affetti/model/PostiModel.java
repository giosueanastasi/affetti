package it.pittysoft.affetti.model;

import java.util.Date;

import lombok.Data;

@Data
public class PostiModel {
	private String nome;
	private String cognome;
	private String loculo;
	private String fornice;
	private Date scadenza;
	private String stato;
	private Boolean checked = false; 
}
