package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
public class Comuni {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message="{NotNull.Comune.nome}")
    private String nome;
    
    @Column
    @NotNull(message="{NotNull.Contraente.nome}")
    private String codComuneIstat;
    
    @Column
    @NotNull(message="{NotNull.Contraente.nome}")
    private String codCatastComune;
    
    @Column
    @NotNull(message="{NotNull.Contraente.provincia}")
    private String provincia;
    
    @Column
    @NotNull(message="{NotNull.Contraente.nome}")
    private String codProv;
    
    @Column
    @NotNull(message="{NotNull.Contraente.nome}")
    private String descProv;
    
    @Column
    @NotNull(message="{NotNull.Contraente.nome}")
    private String codReg;
    
    @Column
    private String descReg;
   
}
