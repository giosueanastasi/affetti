package it.pittysoft.affetti.model;

import lombok.Data;

@Data
public class PostiModel {
	private String nome;
	private String cognome;
	private String loculo;
	private String fornice;
	private Boolean checked = false; 
}
