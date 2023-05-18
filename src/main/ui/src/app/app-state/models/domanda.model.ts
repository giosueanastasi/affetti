export class Domanda {
  id: number;
  protocollo: string;
  data_protocollo: string;
  stato: string;
  tipologia: string;
  nome: string;
  cognome: string;
  nomeAss: string;
  cognomeAss: string;
  comune_nascita: String;
	provincia_nascita: String;
	stato_nascita: String;
	comune_residenza: String;
	provincia_residenza: String;
	via_residenza: String;
	civico_residenza: String;
	cap_residenza: Number;
  codice_fiscale: string;
  email: string;
  note: string;
  telefono: Number;
  loculo: string;
  fornice: string;
  data: Date;
  data_decesso: Date;
  comune_decesso: string;
  fk_posto: number;
}

