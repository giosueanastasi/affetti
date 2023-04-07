package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
public class Domande {
	
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.Contraente.protocollo}")
    private String protocollo;
    
    @Column
    @NotNull(message="{NotNull.Contraente.data_protocollo}")
    private String data_protocollo;
    
    @Column
    @NotNull(message="{NotNull.Contraente.stato}")
    private String stato;
    
    @Column
    @NotNull(message="{NotNull.Contraente.fk_posto}")
    private int fk_posto;
    
    @Column
    @NotNull(message="{NotNull.Contraente.fk_assegnatario}")
    private int fk_assegnatario;
    
    @Column
    @NotNull(message="{NotNull.Contraente.fk_contraente}")
    private int fk_contraente;
    
    @Column
    private int fk_user_modifier;
    
    @Column
    private String data_insert;
    
    @Column
    private String data_update;
    
   
}

