package it.pittysoft.affetti.model;

import it.pittysoft.affetti.links.AssegnatarioLinks;
import it.pittysoft.affetti.links.CapLinks;
import it.pittysoft.affetti.links.ComuneLinks;
import it.pittysoft.affetti.links.ContraenteLinks;
import it.pittysoft.affetti.links.ContrattoLinks;
import it.pittysoft.affetti.links.DefuntoLinks;
import it.pittysoft.affetti.links.DomandaLinks;
import it.pittysoft.affetti.links.PostoLinks;
import it.pittysoft.affetti.links.TenantLinks;
import it.pittysoft.affetti.links.UserLinks;

public class ApiEndpoints {

	private ApiEndpoints() {}
	public static final String BASE_PATH = "/api";
 
    public static final String[] PUBLIC_ENDPOINTS = {
        "/api/login",
        "/api/register",
        "/h2-console/**",
        BASE_PATH + DefuntoLinks.SEARCH_DEFUNTI,
        BASE_PATH + DefuntoLinks.SEARCH_DEFUNTO,
        BASE_PATH + ComuneLinks.LIST_COMUNI
    };
    
    public static final String[] OPERATOR_ENDPOINTS = {
		BASE_PATH + UserLinks.LIST_USERS ,
		BASE_PATH + UserLinks.SEARCH_USERS ,
		BASE_PATH + AssegnatarioLinks.LIST_ASSEGNATARI,
		BASE_PATH + CapLinks.SEARCH_CAP ,
		BASE_PATH + ComuneLinks.LIST_COMUNI ,
		BASE_PATH + ComuneLinks.GET_COMUNE ,
		BASE_PATH + ContraenteLinks.LIST_CONTRAENTI ,
		BASE_PATH + ContraenteLinks.SEARCH_CONTRAENTI ,
		BASE_PATH + ContrattoLinks.GET_CONTRATTO_BY_PROTOCOLLO ,
		BASE_PATH + ContrattoLinks.LIST_CONTRATTI ,
		BASE_PATH + ContrattoLinks.SEARCH_CONTRATTO ,
		BASE_PATH + ContrattoLinks.STAMPA_CONTRATTO ,
		BASE_PATH + DomandaLinks.LIST_DOMANDE ,
		BASE_PATH + DomandaLinks.SEARCH_DOMANDE ,
		BASE_PATH + DomandaLinks.STAMPA_DOMANDA ,
		BASE_PATH + "/domanda/*" ,
		BASE_PATH + PostoLinks.LIST_POSTI ,
		BASE_PATH + PostoLinks.SEARCH_POSTI ,
		BASE_PATH + TenantLinks.LIST_TENANTS ,
		BASE_PATH + "/tenant/*" ,
		BASE_PATH + "/tenant/slug/*" ,
		BASE_PATH + "/tenant/*/cimiteri" ,
		BASE_PATH + "/tenant/*/search_defunti" ,
		BASE_PATH + "/tenant/*/defunti"
    };
    
    public static final String[] USER_ENDPOINTS = {
		BASE_PATH + CapLinks.SEARCH_CAP ,
		BASE_PATH + ContrattoLinks.GET_CONTRATTO_BY_PROTOCOLLO ,
		BASE_PATH + ContrattoLinks.LIST_CONTRATTI ,
		BASE_PATH + ContrattoLinks.SEARCH_CONTRATTO ,
		BASE_PATH + ContrattoLinks.STAMPA_CONTRATTO ,
		BASE_PATH + DomandaLinks.LIST_DOMANDE ,
		BASE_PATH + DomandaLinks.SEARCH_DOMANDE,
		BASE_PATH + DomandaLinks.STAMPA_DOMANDA ,
		BASE_PATH + ComuneLinks.LIST_COMUNI ,
		BASE_PATH + ComuneLinks.GET_COMUNE ,
		BASE_PATH + UserLinks.PROFILE ,
		BASE_PATH + UserLinks.PROFILE_PASSWORD ,
		BASE_PATH + UserLinks.PROFILE_EMAIL
    };
}
