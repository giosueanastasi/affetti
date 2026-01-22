package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Sepolture {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_defunto")
	@NotNull(message="{NotNull.Sepoltura.defunto}")
	private Defunti defunto;

	@ManyToOne
	@JoinColumn(name = "fk_posto")
	@NotNull(message="{NotNull.Sepoltura.posto}")
	private Posti posto;

	@Column
	@NotNull(message="{NotNull.Sepoltura.data_inizio}")
	private String data_inizio;

	@Column
	private String data_fine;

	@Column
	@NotNull(message="{NotNull.Sepoltura.tipo_operazione}")
	private String tipo_operazione;

	@Column
	private String note;

	@Column
	private String data_insert;

	@Column
	private String data_update;

	@Column
	private String fk_user_modifier;

}
