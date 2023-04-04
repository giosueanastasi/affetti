DROP TABLE IF EXISTS comune;

CREATE TABLE comune(
	id int NOT NULL PRIMARY KEY,
	nome varchar(256) NOT NULL,
	provincia char(2) NOT NULL,
);

	INSERT INTO comune (id, nome,provincia) VALUES
  (1, 'Sant Egidio alla vibrata', 'TE'),
  (2, 'Ascoli Piceno', 'AP'),
  (3, 'Giulianova', 'TE'); 
	
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

INSERT INTO assegnatario (id, nome,cognome,data_decesso,comune_decesso,data_update,data_insert,fk_user_modifer) VALUES
  (1, 'Stefano', 'Bandello','20-09-2023','Sant''Egidio alla Vibrata', '25-09-2023','25-09-2023',1),
  (2, 'Vincenzo','D''Auri', '4-02-2020','Ascoli Piceno','04-03-2023','8-02-2020',3),
  (3, 'Samuel','Feliciani', '5-04-2023''Giulianova', '8-04-2023','8-04-2023',2); 
	

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

INSERT INTO posto (id, loculo,fornice,tipo,fk_user_modifier,data_update,data_insert) VALUES
  (1, 15, 150, 'intermedia' ,2, '25-09-2023','25-09-2023'),
  (2, 1, 8, 'monumentale',3,'04-03-2023','8-02-2020'),
  (3, 8,120, 'nuova',1, '8-04-2023','8-04-2023'); 
	

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT NOT NULL,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(50) NOT NULL,
  ruolo VARCHAR(10) NOT NULL,
  fk_comune int NOT NULL,
);

INSERT INTO users (id, username,password,ruolo,fk_comune) VALUES
  (1,' Antonio90','dcunwencwebci', 'utente',2),
  (2, 'Stefano24', 'qecwebckw','utente',1),
  (3, 'Giovanna98', 'ugcywie', 'utente',3); 

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
	codice_fiscale varchar (16) NOT NULL,
	email varchar(256) NULL,
	fk_user_modifer int NULL,
	note text NULL,
	fk_user_modifer int NULL,
	data_insert date NULL,
	data_update date NULL,
);

INSERT INTO contraente (id, nome,cognome,comune_nascita,provincia_nascita,stato_nascita,data_nascita,comune_residenza,provincia_residenza,via_residenza,civico_residenza,cap_residenza,telefono,codice_fiscale,email,note text,fk_user_modifer,data_insert,data_update) VALUES
  (1,' Antonio','Bandello', 'Sant''Omero','TE','Italia','20-09-1998','Sant''Egidio alla Vibrata','TE','Via Vibrata',27,64016,0861840071,'BNDNTN98P20I348D','bandelloantonio@libero.it','',2,'25-09-2023','25-09-2023'),
  (2, 'Antonietta', 'Di Giuseppe','Ascoli Piceno','AP','Italia','15-08-1990','Monticelli','AP','Via dei Sanniti',3'/B',63100,3277665754,'DGPANT90P15L387J','','',1,'04-03-2023','8-02-2020'),
  (3, 'Giovanna','Di Saverio','Giulianova','TE','Italia','31-01-1970','Giulianova','TE','Via Liguria',2 'SCALA A',64021,3475700123,'DSRGVN70P31I351S','','',3,'8-04-2023','8-04-2023'); 

DROP TABLE IF EXISTS domanda;

CREATE TABLE domanda (
	id int NOT NULL PRIMARY KEY,
	protocollo varchar(10) NOT NULL,
	data_protocollo date NOT NULL,
	stato varchar(20) NOT NULL,
	fk_posto int NOT NULL,
	fk_assegnatario int NOT NULL,
	fk_contraente int NOT NULL,
	data_insert date NULL,
	data_update date NULL,
	fk_user_modifer int NULL
	
	
	INSERT INTO domanda (id, protocollo,data_protocollo,stato,fk_posto,fk_assegnatario,fk_contraente,data_insert,data_update,fk_user_modifer) VALUES
  (1,506,'25-09-2023','in lavorazione',2,1,3,2, '25-09-2023','25-09-2023'),
  (2,204, '04-03-2023','eseguito',1,2,1,3,'04-03-2023','8-02-2020'),
  (3, 890, '8-04-2023', 'sto elaborando',3,3,2,1, '8-04-2023','8-04-2023'); 
	
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

INSERT INTO contratto (id, protocollo,data_inizio,data_scadenza,stato,fk_domanda_loculo,fk_domanda_disposizione,data_insert,data_update,fk_user_modifer) VALUES
  (1,106,'25-09-2023','25-09-2058','in lavorazione',2,3, '25-09-2023','25-09-2023',1),
  (2,405, '04-03-2023','04-03-2058','eseguito',1,2,'04-03-2023','8-02-20200',2),
  (3, 450, '8-04-2023', '8-04-2058''sto elaborando',3,1, '8-04-2023','8-04-2023',3); 
