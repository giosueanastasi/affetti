package it.pittysoft.affetti.dto;

import lombok.Data;

@Data
public class DefuntoCardDto {

    private Long id;
    private String codice;
    private String nome;
    private String cognome;
    private String data_nascita;
    private String comune_nascita;
    private String provincia_nascita;
    private String data_decesso;
    private String comune_decesso;
    private String provincia_decesso;
    private String immagine_url;
    private String elogio_funebre;

    // Quick-access IDs — null when the related entity does not exist
    private Long domandaId;
    private Long contrattoId;

}
