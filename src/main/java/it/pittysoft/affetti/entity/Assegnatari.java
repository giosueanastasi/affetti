package it.pittysoft.affetti.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Entity
@Data
public class Assegnatari {
	
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message="{NotNull.Assegnatario.nome}")
    private String nome;
    
    @Column
    @NotNull(message="{NotNull.Assegnatario.cognome}")
    private String cognome;
    
    @Column
    private String data_decesso;
    
    @Column
    private String comune_decesso;
    
    @Column
    private String fk_user_modifier;
    
    @Column
    private String data_update;
    
    @Column
    private String data_insert; 
    
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy="assegnatario")
//    private Set<Domande> domande;
    
    @OneToMany(mappedBy = "assegnatario")
    private List<Domande> domande  = new ArrayList<>();
    

}
