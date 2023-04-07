package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Entity
@Data
public class Assegnatari {
	
	
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.Assegnatario.nome}")
    private String nome;
    
    @Column
    @NotNull(message="{NotNull.Assegnatario.cognome}")
    private String cognome;
    
    @Column
    @NotNull(message="{NotNull.Assegnatario.data_decesso}")
    private String data_decesso;
    
    @Column
    @NotNull(message="{NotNull.Assegnatario.comune_decesso}")
    private String comune_decesso;
    
    @Column
    @NotNull(message="{NotNull.Assegnatario.fk_user_modifier}")
    private String fk_user_modifier;
    
    @Column
    @NotNull(message="{NotNull.Assegnatario.data_update}")
    private String data_update;
    
    @Column
    @NotNull(message="{NotNull.Assegnatario.data_insert}")
    private String data_insert; 

}
