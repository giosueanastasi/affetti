export class Contratto {
  id: number;
  protocollo: string;
  data_inizio: Date;
  data_scadenza: Date;
  stato: string;
  fk_domanda_loculo: number;
  fk_domanda_disposizione: number;
  fk_user_modifier: number;
  data_update: Date;
  data_insert: Date;

  
  nomeA : string;
  nomeC : string;
  cognomeA : string;
  cognomeC : string;


    idContratto:number;
	numeroProtocolloContratto: string;
	dataProtocolloContratto: Date;
	dataScadenzaContratto: Date;

    idContraente:number;
	codice_fiscale: string;
	comune_nascita: string;
	provincia_nascita: string;
	stato_nascita: string;
	data_nascita: string;
	comune_residenza: string;
    provincia_residenza: string;
	via_residenza: string;
	civico_residenza: string;
	cap_residenza: string;
	email: string;
	note: string;
	telefono: string;
	
	idPosto:number;
	loculo: string;
	fornice: string;
	
	idAssegnatario: number;
	comune_decesso: string;
	data_decesso: string;
	
	idDomanda:number;
	protocolloDomanda: string;
	dataProtocolloDomanda: string;


}
