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
public class Posti {
	
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message="{NotNull.Posto.fornice}")
    private String fornice;
    
    @Column
    @NotNull(message="{NotNull.Posto.loculo}")
    private String loculo;
    
    @Column
    @NotNull(message="{NotNull.Posto.tipo}")
    private String tipo;
    
    
    @Column
    private String data_insert;
    
    @Column
    private String data_update;
    
    @Column
    private String fk_user_modifier;
    
    
    

}
