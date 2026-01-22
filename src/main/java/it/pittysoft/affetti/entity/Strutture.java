package it.pittysoft.affetti.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Strutture {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@NotNull(message="{NotNull.Struttura.codice}")
	private String codice;

	@Column
	private String nome;

	@Column
	private String descrizione;

	@Column(precision = 10, scale = 8)
	private BigDecimal latitudine;

	@Column(precision = 11, scale = 8)
	private BigDecimal longitudine;

	@Column
	private Boolean attiva = true;

	@Column
	private Integer capienza;

	@Column
	private String data_insert;

	@Column
	private String data_update;

	@Column
	private String fk_user_modifier;

	@ManyToOne
	@JoinColumn(name = "fk_area", nullable = false)
	@NotNull(message="{NotNull.Struttura.area}")
	private Aree area;

	@OneToMany(mappedBy = "struttura")
	@JsonIgnore
	private List<Posti> posti = new ArrayList<>();

}
