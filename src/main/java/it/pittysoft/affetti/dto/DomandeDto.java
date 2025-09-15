package it.pittysoft.affetti.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DomandeDto {

    private Long id;
    private String protocollo;
    private Date data_protocollo;
    private String stato;
    private String tipologia;


    private PostiDto posto;
    private AssegnatariDto assegnatario;
    private ContraentiDto contraente;


}