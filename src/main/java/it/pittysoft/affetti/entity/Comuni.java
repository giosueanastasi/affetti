package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
public class Comuni {
	
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.Contraente.nome}")
    private String nome;
    
    @Column
    @NotNull(message="{NotNull.Contraente.provincia}")
    private String provincia;
   
}
