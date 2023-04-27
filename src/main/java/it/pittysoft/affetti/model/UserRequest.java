package it.pittysoft.affetti.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserRequest extends Request{
    private long id;
    private String username;
    private String password;
    private String ruolo;
    private String fk_comune;
}
