package it.pittysoft.affetti.model;

import lombok.Data;

@Data
public class PostiRequest extends Request{
	private String nome;
	private String cognome;
	private String loculo;
	private String fornice;
}
