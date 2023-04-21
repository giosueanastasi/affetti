package it.pittysoft.affetti.entity;

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


import lombok.Data;

@Entity
@Data
public class Domande {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message="{NotNull.Contraente.protocollo}")
    private String protocollo;
    
    @Column
    @NotNull(message="{NotNull.Contraente.data_protocollo}")
    private String data_protocollo;
    
    @Column
    @NotNull(message="{NotNull.Contraente.stato}")
    private String stato;
    
//    @Column
//    @NotNull(message="{NotNull.Contraente.fk_assegnatario}")
//    private Long fk_assegnatario;
    
    @Column
    @NotNull(message="{NotNull.Contraente.fk_contraente}")
    private Long fk_contraente;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_assegnatario")
    private Assegnatari assegnatario;
    
    @Column
    private Long fk_user_modifier;
    
    @Column
    private String data_insert;
    
    @Column
    private String data_update;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_posto")
    private Posti posto;
    
    @OneToOne(mappedBy = "domanda")
    private Contratti contratto;
    
    
   
}

