package it.pittysoft.affetti.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    
//    @Column
//    @NotNull(message="{NotNull.User.fk_ruolo}")
//    private String  fk_ruolo;
    
    @Column
    @NotNull(message="{NotNull.User.fk_comune}")
    private String fk_comune;

    @Column
    private String email;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    		name = "role_users",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "role_id")
    		)
	private Set<Role> roles;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_tenant")
	private Tenant tenant;


}
