package it.pittysoft.affetti.model;

import lombok.Data;

@Data
public class ComuniModel  {
	
	private Long id;
	private String nome;
	private String codComuneIstat;
	private String codCatastComune;
	private String provincia;
	private String codProv;
	private String descProv;
	private String codReg;
	private String descReg;

}
