package it.pittysoft.affetti.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class TipiSepoltura {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull(message="{NotNull.TipoSepoltura.codice}")
	private String codice;

	@Column
	private String descrizione;

	@Column
	@NotNull(message="{NotNull.TipoSepoltura.attivo}")
	private Boolean attivo;

	@Column
	private String data_insert;

	@Column
	private String data_update;

	@OneToMany(mappedBy = "tipoSepoltura")
	@JsonIgnore
	private List<Posti> posti = new ArrayList<>();

}
