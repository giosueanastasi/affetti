package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Defunti {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull(message="{NotNull.Defunto.nome}")
	private String nome;
	
	@Column
	@NotNull(message="{NotNull.Defunto.cognome}")
	private String cognome;
	
	@Column
	@NotNull(message="{NotNull.Defunto.data_nascita}")
	private String data_nascita;
	
	@Column
	@NotNull(message="{NotNull.Defunto.comune_nascita}")
	private String comune_nascita;
	
	@Column
	@NotNull(message="{NotNull.Defunto.provincia_nascita}")
	private String provincia_nascita;
	
	@Column
	@NotNull(message="{NotNull.Defunto.data_decesso}")
	private String data_decesso;
	
	@Column
	@NotNull(message="{NotNull.Defunto.comune_decesso}")
	private String comune_decesso;
	
	@Column
	@NotNull(message="{NotNull.Defunto.provincia_decesso}")
	private String provincia_decesso;
	
	@Column
	private String immagine_url;
	
	@Column
	private String elogio_funebre;
	
    @Column
    private String data_insert;
    
    @Column
    private String data_update;
	
	
	
}
