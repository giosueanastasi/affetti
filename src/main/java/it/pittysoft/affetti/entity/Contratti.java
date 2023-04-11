package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Entity
@Data
public class Contratti {
	
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String protocollo;
    
    @Column
    @NotNull(message="{NotNull.Contratto.data_inizio}")
    private String data_inizio;
    
    @Column
    private String data_scadenza;
    
    @Column
    @NotNull(message="{NotNull.Contratto.stato}")
    private String stato;
    
    @Column
    @NotNull(message="{NotNull.Contratto.fk_domanda_loculo}")
    private String fk_domanda_loculo;
    
    @Column
    private String fk_domanda_disposizione;
    
    @Column
    private String fk_user_modifier;
    
    @Column
    private String data_update;
    
    @Column
    private String data_insert;
    
}
