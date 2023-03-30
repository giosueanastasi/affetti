package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Entity
@Data
public class Domanda {
	
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.Domanda.protocollo}")
    private long protocollo;
    
    @Column
    @Null(message="{Null.Domanda.stato}")
    private String stato;
    
    @Column
    @NotNull(message="{NotNull.Domanda.utenti}")
    private String utenti;
    
    @Column
    @NotNull(message="{NotNull.Domanda.data_insert}")
    private String data_insert;
   
    @Column
    @NotNull(message="{NotNull.Domanda.data_update}")
    private String data_update;
    
    @Column
    @NotNull(message="{NotNull.Domanda.fk_contraente}")
    private long fk_contraentet;
    
    

}
