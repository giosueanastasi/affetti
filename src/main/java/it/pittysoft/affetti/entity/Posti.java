package it.pittysoft.affetti.entity;



import java.util.ArrayList;
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
    private String tipo;
    
    @Column
    @NotNull(message="{NotNull.Posto.stato}")
    private String stato;

    
    @Column
    private String data_insert;
    
    @Column
    private String data_update;
    
    @Column
    private String fk_user_modifier;
    

    @OneToMany(mappedBy = "posto")
    private List<Domande> domande  = new ArrayList<>();
   

    


    
    

}
