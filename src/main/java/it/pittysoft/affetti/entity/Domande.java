package it.pittysoft.affetti.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import lombok.Data;

@Entity
@Data
public class Domande {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message="{NotNull.Domanda.protocollo}")
    private String protocollo;
    
    @Column
    @NotNull(message="{NotNull.Domanda.data_protocollo}")
    private Date data_protocollo;
    
    @Column
    @NotNull(message="{NotNull.Domanda.stato}")
    private String stato;
        
    
    @Column
    private Long fk_user_modifier;
    
    @Column
    private String data_insert;
    
    @Column
    private String data_update;
    
    @Column
    private String tipologia;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_posto")
    private Posti posto;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_assegnatario")
    private Assegnatari assegnatario;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_contraente")
    private Contraenti contraente;
    
    
    @OneToOne(mappedBy = "domanda")
    private Contratti contratto;
}

