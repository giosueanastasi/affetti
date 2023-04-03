DROP TABLE IF EXISTS comune;

CREATE TABLE comune(
	id int NOT NULL PRIMARY KEY,
	nome varchar(256) NOT NULL,
	provincia char(2) NOT NULL,
);

	DROP TABLE IF EXISTS assegnatario;

	CREATE TABLE assegnatario (
	id int NOT NULL PRIMARY KEY,
	nome varchar(256) NOT NULL,
	cognome varchar(256) NOT NULL,
	data_decesso date NULL,
	comune_decesso date NULL,
	data_update date NULL,
	data_insert date NULL,
	fk_user_modifer int NULL,
);

DROP TABLE IF EXISTS posto;

CREATE TABLE posto (
	id INT NOT NULL PRIMARY KEY,
	loculo VARCHAR(10) NOT NULL,
	fornice VARCHAR(10) NOT NULL,
	tipo VARCHAR (10) NOT NULL,
	fk_user_modifier int NULL,
	data_update date NULL,
	data_insert date NULL,
);


DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT NOT NULL,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(50) NOT NULL,
  ruolo VARCHAR(10) NOT NULL,
  fk_comune int NOT NULL,
);


DROP TABLE IF EXISTS contraente;

CREATE TABLE contraente (
	id int NOT NULL PRIMARY KEY,
	nome varchar(256) NOT NULL,
	cognome varchar(256) NOT NULL,
	comune_nascita varchar (256) NOT NULL,
	provincia_nascita char(2) NOT NULL,
	stato_nascita varchar (150) NOT NULL,
	data_nascita date NOT NULL,
	comune_residenza varchar(256) NOT NULL,
	provincia_residenza char(2) NOT NULL,
	via_residenza varchar(256) NOT NULL,
	civico_residenza varchar(20) NOT NULL,
	cap_residenza varchar (10) NOT NULL,
	telefono varchar(20) NOT NULL,
	codice_fiscale varchar (50) NOT NULL,
	email varchar(256) NULL,
	fk_user_modifer int NULL,
	note text NULL,
	fk_user_modifer int NULL,
	data_insert date NULL,
	data_update date NULL,
);

DROP TABLE IF EXISTS domanda;

CREATE TABLE domanda (
	id int NOT NULL PRIMARY KEY,
	protocollo varchar(10) NOT NULL,
	data_protocollo date NOT NULL,
	stato varchar(10) NOT NULL,
	fk_posto int NOT NULL,
	fk_assegnatario int NOT NULL,
	fk_contraente int NOT NULL,
	data_insert date NULL,
	data_update date NULL,
	fk_user_modifer int NULL
	
	
	DROP TABLE IF EXISTS contratto;
	
	CREATE TABLE contratto (
	id int NOT NULL,
	protocollo varchar(256) NULL,
	data_inizio date NOT NULL,
	data_scadenza date  NULL,
	stato varchar(50) NOT NULL,
	fk_domanda_loculo int NOT NULL,
	fk_domanda_disposizione int NULL
	data_update date NULL,
	data_insert date NULL
	fk_user_modifer int NULL
);
