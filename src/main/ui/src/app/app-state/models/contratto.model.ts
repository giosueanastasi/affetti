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


}
