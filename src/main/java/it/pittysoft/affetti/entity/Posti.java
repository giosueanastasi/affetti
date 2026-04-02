package it.pittysoft.affetti.entity;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class Posti {
	
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codice;

    @Column
    @NotNull(message="{NotNull.Posto.fornice}")
    private String fornice;
    
    @Column
    @NotNull(message="{NotNull.Posto.loculo}")
    private String loculo;
    
    @ManyToOne
    @JoinColumn(name = "fk_tipo_sepoltura")
    private TipiSepoltura tipoSepoltura;
    
    @Column
    @NotNull(message="{NotNull.Posto.stato}")
    private String stato;

    
    @Column
    private String data_insert;
    
    @Column
    private String data_update;
    
    @Column
    private String fk_user_modifier;

    @Column
    private String fila;

    @Column
    private String numero;

    @Column
    private String piano;

    @ManyToOne
    @JoinColumn(name = "fk_area")
    private Aree area;

    @ManyToOne
    @JoinColumn(name = "fk_struttura")
    private Strutture struttura;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitudine;

    @Column(precision = 11, scale = 8)
    private BigDecimal longitudine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tenant")
    private Tenant tenant;

    @OneToMany(mappedBy = "posto")
    @JsonIgnore
    private List<Domande> domande  = new ArrayList<>();

    @OneToMany(mappedBy = "posto")
    @JsonIgnore
    private List<Defunti> defunti = new ArrayList<>();
   

    


    
    

}
