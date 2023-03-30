package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Entity
@Data
public class Contraente {
	
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.Contraente.nome}")
    private String nome;
    
    @Column
    @NotNull(message="{NotNull.Contraente.cognome}")
    private String cognome;
    
    @Column
    @NotNull(message="{NotNull.Contraente.telefono}")
    private int telefono;
    
    @Column
    @NotNull(message="{NotNull.Contraente.indirizzo_residenza}")
    private String indirizzo_residenza;
    		
    @Column
    @Null(message="{Null.Contraente.indirizzo_domicilio}")
    private String indirizzo_domicilio;
    
    @Column
    @NotNull(message="{NotNull.Contraente.codice_fiscale}")
    private String codice_fiscale;
    
    @Column
    @NotNull(message="{NotNull.Contraente.cap}")
    private int cap;
    		
    @Column
    @Null(message="{Null.Contraente.email}")
    private String email;
    		
    @Column
    @NotNull(message="{NotNull.Contraente.utenti}")
    private String utenti;
    
    @Column
    @NotNull(message="{NotNull.Contraente.data_insert}")
    private int data_insert;
    
    @Column
    @NotNull(message="{NotNull.Contraente.data_update}")
    private int data_update;
    		
    //CONSTRAINT contraente_pkey PRIMARY KEY (id)
    		

}
