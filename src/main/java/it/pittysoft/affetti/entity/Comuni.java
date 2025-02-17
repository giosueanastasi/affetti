package it.pittysoft.affetti.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Comuni {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull(message="{NotNull.Comune.nome}")
    private String nome;
    
    @Column(unique = true)
    @NotNull(message="{NotNull.Comune.codComuneIstat}")
    private String codComuneIstat;
    
    @Column
    @NotNull(message="{NotNull.Comune.codCatastComune}")
    private String codCatastComune;
    
    @Column
    @NotNull(message="{NotNull.Comune.provincia}")
    private String provincia;
    
    @Column
    @NotNull(message="{NotNull.Comune.codProv}")
    private String codProv;
    
    @Column
    @NotNull(message="{NotNull.Comune.descProv}")
    private String descProv;
    
    @Column
    @NotNull(message="{NotNull.Comune.codReg}")
    private String codReg;
    
    @Column
    private String descReg;
    
    @Column
    @NotNull(message="{NotNull.Comune.nome}")
    private String stato;
    
    @ManyToMany() 
    @JoinTable(
    name = "cap_comuni",
    joinColumns=  @JoinColumn(name = "comune_id"),
    inverseJoinColumns=  @JoinColumn(name = "cap_id")
    )
    private List<Cap> listaCap;
    
}
