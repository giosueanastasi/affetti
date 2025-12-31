package it.pittysoft.affetti.entity;


import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private LocalDateTime data_update;
    
    @Column
    private LocalDateTime data_insert;
    
    @PrePersist
    private void onSave() {
        data_insert = LocalDateTime.now();
        data_update = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        data_update = LocalDateTime.now();
    }
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_domanda")
    @JsonBackReference
    private Domande domanda;
    
}
