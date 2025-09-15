package it.pittysoft.affetti.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AssegnatariDto {

    private Long id;
    private String nome;
    private String cognome;
    private String comune_decesso;
    private Date data_decesso;
    
}
