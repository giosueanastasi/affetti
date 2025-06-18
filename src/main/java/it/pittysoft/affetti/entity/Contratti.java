package it.pittysoft.affetti.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private Date data_inizio;
    
    @Column
    private Date data_scadenza;
    
    @Column
    @NotNull(message="{NotNull.Contratto.stato}")
    private String stato;
    
    @Column
    private String fk_user_modifier;
    
    @Column
    private String data_update;
    
    @Column
    private String data_insert;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_domanda")
    @JsonBackReference
    private Domande domanda;
    
}
