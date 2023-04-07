export class Contraente {
  	id: number;
	nome: String;
	cognome: String;
	comune_nascita: String;
	provincia_nascita: String;
	stato_nascita: String;
	data_nascita: Date;
	comune_residenza: String;
	provincia_residenza: String;
	via_residenza: String;
	civico_residenza: String;
	cap_residenza: Number;
	telefono: Number;
	codice_fiscale: String;
	email: String;
	note: String;
	fk_user_modifier: Number;
	data_insert: Date;
	data_update: Date;
}