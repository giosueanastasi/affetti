package it.pittysoft.affetti.utils;

import java.util.Arrays;
import java.util.List;

import it.pittysoft.affetti.entity.Domande;

public class Protocollo {
	
	public static String generaProtocolloDomanda(List<Domande> domande) {
		
		String nuovoProtocollo;
		
		int protocolli[] =  new int[domande.size()];
    	
    	for(int i = 0; i < domande.size(); i++){
    		protocolli[i] = Integer.parseInt(domande.get(i).getProtocollo());
    	}
    	
    	Arrays.sort(protocolli);
    	
    	nuovoProtocollo = String.valueOf(protocolli[protocolli.length - 1] + 1);
   
    	return nuovoProtocollo;
    	
	}

}
