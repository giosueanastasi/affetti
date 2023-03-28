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