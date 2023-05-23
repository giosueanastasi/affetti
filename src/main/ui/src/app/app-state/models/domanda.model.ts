export class Domanda {

  idDomanda: number;
  idPosto: number;
  idAssegnatario : number;
  idContraente: number;
  numeroProtocolloDomanda: string;
  dataProtocollo: string;
  stato: string;
  tipologia: string;
  nomeContraente: string;
  cognomeContraente: string;
  nomeAss: string;
  cognomeAss: string;
  comuneDiNascita: String;
	provinciaDiNascita: String;
	statoDiNascita: String;
	comuneDiResidenza: String;
	provinciaDiResidenza: String;
	viaDiResidenza: String;
	civicoDiResidenza: String;
	capDiResidenza: Number;
  codiceFiscale: string;
  email: string;
  note: string;
  telefono: Number;
  loculo: string;
  fornice: string;
  data: Date;
  dataDecesso: Date;
  comuneDecesso: string;
  fk_posto: number;
  fk_assegnatario : number;
}

