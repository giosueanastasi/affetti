package it.pittysoft.affetti.links;

import org.springframework.stereotype.Component;

@Component
public class DomandaLinks {
	
	public static final String LIST_DOMANDE = "/domande";
    public static final String ADD_DOMANDA = "/domanda";
    public static final String GET_DOMANDA = "/domanda/{id}";
    public static final String ADD_DOMANDA_FULL = "/domandaFull";
    public static final String SEARCH_DOMANDE = "/search_domande";
    public static final String GENERA_PROTOCOLLO = "/genera_protocollo_domanda";
    public static final String STAMPA_DOMANDA = "/stampa_domanda/{idDomanda}";
}
