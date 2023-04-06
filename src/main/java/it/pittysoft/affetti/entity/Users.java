package it.pittysoft.affetti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
public class Users {
	
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.User.username}")
    private String username;
    
    @Column
    @NotNull(message="{NotNull.User.password}")
    private String password;
    
    @Column
    @NotNull(message="{NotNull.User.ruolo}")
    private String ruolo;
    
    @Column
    @NotNull(message="{NotNull.User.fk_comune}")
    private String fk_comune;


}
