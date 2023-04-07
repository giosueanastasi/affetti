package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Entity
@Data
public class Contratti {
	
	
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.Contratto.protocollo}")
    private String protocollo;
    
    @Column
    @NotNull(message="{NotNull.Contratto.data_inizio}")
    private String data_inizio;
    
    @Column
    @NotNull(message="{NotNull.Contratto.data_scadenza}")
    private String data_scadenza;
    
    @Column
    @NotNull(message="{NotNull.Contratto.stato}")
    private String stato;
    
    @Column
    @NotNull(message="{NotNull.Contratto.fk_domanda_loculo}")
    private String fk_domanda_loculo;
    
    @Column
    @NotNull(message="{NotNull.Contratto.fk_domanda_disposizione}")
    private String fk_domanda_disposizione;
    
    @Column
    @NotNull(message="{NotNull.Contratto.fk_user_modifier}")
    private String fk_user_modifier;
    
    @Column
    @NotNull(message="{NotNull.Contratto.data_update}")
    private String data_update;
    
    @Column
    @NotNull(message="{NotNull.Contratto.data_insert}")
    private String data_insert;
    
}
