CREATE TABLE affetti_svil.domanda (
	id serial4 NOT NULL,
	protocollo int4 NOT NULL,
	data_domanda date NOT NULL,
	stato varchar NOT NULL,
	utenti varchar NOT NULL,
	data_insert date NOT NULL,
	data_update date NOT NULL,
	fk_contraente serial4 NOT NULL,
	CONSTRAINT domanda_pkey PRIMARY KEY (id),
	CONSTRAINT domanda_fk_contraente_fkey FOREIGN KEY (fk_contraente) REFERENCES affetti_svil.contraente(id)
);

CREATE TABLE affetti_svil.contratto (
	data_contratto date NOT NULL,
	scadenza_contratto date NOT NULL,
	stato varchar NOT NULL,
	utenti varchar NOT NULL,
	data_insert date NOT NULL,
	data_update date NOT NULL
);

CREATE TABLE affetti_svil.assegnatario (
	nome varchar NOT NULL,
	cognome varchar NOT NULL,
	dati_anagrafici varchar NOT NULL,
	utenti varchar NOT NULL,
	data_insert date NOT NULL,
	data_update date NOT NULL
);