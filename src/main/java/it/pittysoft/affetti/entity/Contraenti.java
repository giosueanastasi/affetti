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
public class Contraenti {
	

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message="{NotNull.Contraente.nome}")
    private String nome;
    
    @Column
    @NotNull(message="{NotNull.Contraente.cognome}")
    private String cognome;
    
    @Column
    @NotNull(message="{NotNull.Contraente.comune_nascita}")
    private String comune_nascita;
    
    @Column
    @NotNull(message="{NotNull.Contraente.provincia_nascita}")
    private String provincia_nascita;
    
    @Column
    @NotNull(message="{NotNull.Contraente.stato_nascita}")
    private String stato_nascita;
    
    @Column
    @NotNull(message="{NotNull.Contraente.data_nascita}")
    private String data_nascita;
    
    @Column
    @NotNull(message="{NotNull.Contraente.comune_residenza}")
    private String comune_residenza;
    
    @Column
    @NotNull(message="{NotNull.Contraente.provincia_residenza}")
    private String provincia_residenza;
    
    @Column
    @NotNull(message="{NotNull.Contraente.via_residenza}")
    private String via_residenza;
    
    @Column
    @NotNull(message="{NotNull.Contraente.civico_residenza}")
    private String civico_residenza;
    
    @Column
    @NotNull(message="{NotNull.Contraente.cap_residenza}")
    private String cap_residenza;
    
    @Column
    @NotNull(message="{NotNull.Contraente.telefono}")
    private String telefono;
    
    @Column
    @NotNull(message="{NotNull.Contraente.codice_fiscale}")
    private String codice_fiscale;
    
    @Column
    private String email;
    
    @Column
    private String note;
    
    @Column
    private String fk_user_modifier;

    @Column
    private String data_insert;
    
    @Column
    private String data_update;

}
