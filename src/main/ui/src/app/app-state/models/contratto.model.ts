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

	numeroProtocolloContratto: string;
	dataProtocolloContratto: Date;
	dataScadenzaContratto: Date;

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
	
	loculo: string;
	fornice: string;
	
	comune_decesso: string;
	data_decesso: string;
	
	protocolloDomanda: string;
	dataProtocolloDomanda: string;


}
