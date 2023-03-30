package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Entity
@Data
public class Posto {
	
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.Posto.fornice}")
    private String fornice;
    
    @Column
    @NotNull(message="{NotNull.Posto.loculo}")
    private String loculo;
   
    @Column
    @Null(message="{Null.Posto.tipo}")
    private String tipo;
    
    @Column
    @NotNull(message="{NotNull.Posto.utenti}")
    private String utenti;
    
    @Column
    @Null(message="{Null.Posto.data_insert}")
    private String data_insert;
    
    @Column
    @NotNull(message="{NotNull.Posto.data_update}")
    private String data_update;
    

}
