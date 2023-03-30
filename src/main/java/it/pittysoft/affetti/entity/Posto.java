package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
public class Posto {
	
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.Posto.loculo}")
    private String loculo;
    
    @Column
    @NotNull(message="{NotNull.Posto.loculo}")
    private String fornice;
    

}
