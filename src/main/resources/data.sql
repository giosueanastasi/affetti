DROP TABLE IF EXISTS comune;

CREATE TABLE comune(
	id int NOT NULL,
	nome varchar(256) NOT NULL,
	provincia char(2) NOT NULL,
	PRIMARY KEY (id)
);

	INSERT INTO comune (id, nome,provincia) VALUES
  (1, 'Sant Egidio alla vibrata', 'TE'),
  (2, 'Ascoli Piceno', 'AP'),
  (3, 'Giulianova', 'TE'); 
	
  
  DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT NOT NULL,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(50) NOT NULL,
  ruolo VARCHAR(10) NOT NULL,
  fk_comune int NOT NULL,
  PRIMARY KEY (id),
  foreign key (fk_comune) references comune (id)
);

INSERT INTO users (id, username,password,ruolo,fk_comune) VALUES
  (1,' Antonio90','dcunwencwebci', 'utente',2),
  (2, 'Stefano24', 'qecwebckw','utente',1),
  (3, 'Giovanna98', 'ugcywie', 'utente',3); 
  
	DROP TABLE IF EXISTS assegnatario;

	CREATE TABLE assegnatario (
	id int NOT NULL,
	nome varchar(256) NOT NULL,
	cognome varchar(256) NOT NULL,
	data_decesso date NULL,
	comune_decesso varchar NULL,
	fk_user_modifier  int NULL,
	data_update date NULL,
	data_insert date NULL,
	PRIMARY KEY (id),
	FOREIGN key(fk_user_modifier ) references users(id)
);

INSERT INTO assegnatario (id, nome,cognome,data_decesso,comune_decesso,data_update,data_insert,fk_user_modifier ) VALUES
  (1, 'Stefano', 'Bandello','2023-09-20','Sant Egidio alla Vibrata', '2023-09-20','2023-09-25',1),
  (2, 'Vincenzo','D Auri', '2020-02-4','Ascoli Piceno','2023-03-5','2020-02-8',3),
  (3, 'Samuel','Feliciani', '2023-04-5','Giulianova', '2023-04-8','2023-04-8',2); 
	

DROP TABLE IF EXISTS posto;

CREATE TABLE posto (
	id INT NOT NULL,
	loculo VARCHAR(10) NOT NULL,
	fornice VARCHAR(10) NOT NULL,
	tipo VARCHAR (15) NOT NULL,
	fk_user_modifier  int NULL,
	data_update date NULL,
	data_insert date NULL,
	PRIMARY KEY (id),
	FOREIGN key(fk_user_modifier ) references users (id)
);

<<<<<<< HEAD
INSERT INTO posto (id, loculo,fornice,tipo,fk_user_modifier ,data_update,data_insert) VALUES
  (1, 15, 150, 'intermedia' ,2, '2023-09-25','2023-09-25'),
  (2, 1, 8, 'monumentale',3,'2023-03-04','2020-02-8'),
  (3, 8,120, 'nuova',1, '2023-04-8','2023-04-8'); 
	


DROP TABLE IF EXISTS contraente;

CREATE TABLE contraente (
	id int NOT NULL,
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
	note text NULL,
	fk_user_modifier  int NULL,
	data_insert date NULL,
	data_update date NULL,
	PRIMARY KEY (id),
	FOREIGN key(fk_user_modifier ) references users (id)
);

INSERT INTO contraente (id, nome,cognome,comune_nascita,provincia_nascita,stato_nascita,data_nascita,comune_residenza,provincia_residenza,via_residenza,civico_residenza,cap_residenza,telefono,codice_fiscale,email,note,fk_user_modifier ,data_insert,data_update) VALUES
  (1,' Antonio','Bandello', 'Sant Omero','TE','Italia','1998-09-20','Sant Egidio alla Vibrata','TE','Via Vibrata',27,64016,0861840071,'BNDNTN98P20I348D','bandelloantonio@libero.it','',2,'2023-09-25','2023-09-25'),
  (2, 'Antonietta', 'Di Giuseppe','Ascoli Piceno','AP','Italia','1990-08-15','Monticelli','AP','Via dei Sanniti',3,63100,3277665754,'DGPANT90P15L387J','','',1,'2023-03-4','2020-02-8'),
  (3, 'Giovanna','Di Saverio','Giulianova','TE','Italia','1970-01-31','Giulianova','TE','Via Liguria',2,64021,3475700123,'DSRGVN70P31I351S','','',3,'2023-04-8','2023-04-8'); 

DROP TABLE IF EXISTS domanda;

CREATE TABLE domanda (
	id int NOT NULL,
	protocollo varchar(10) NOT NULL,
	data_protocollo date NOT NULL,
	stato varchar(20) NOT NULL,
	fk_posto int NOT NULL,
	fk_assegnatario int NOT NULL,
	fk_contraente int NOT NULL,
	fk_user_modifier  int NULL,
	data_insert date NULL,
	data_update date NULL,
	PRIMARY KEY (id),
	FOREIGN key(fk_user_modifier ) references users (id),
	FOREIGN key(fk_assegnatario) references assegnatario (id),
	FOREIGN key(fk_posto) references posto (id),
	FOREIGN key(fk_contraente) references contraente (id)
	);
	
	INSERT INTO domanda (id, protocollo,data_protocollo,stato,fk_posto,fk_assegnatario,fk_contraente,fk_user_modifier ,data_insert,data_update) VALUES
  (1,506,'2023-09-25','in lavorazione',2,1,3,2, '2023-09-25','2023-09-25'),
  (2,204, '2023-03-04','eseguito',1,2,1,3,'2023-03-04','2020-02-8'),
  (3, 890, '2023-04-8', 'sto elaborando',3,3,2,1, '2023-04-8','2023-04-8'); 
	
	DROP TABLE IF EXISTS contratto;
	
	CREATE TABLE contratto (
	id int NOT NULL,
	protocollo varchar(256) NULL,
	data_inizio date NOT NULL,
	data_scadenza date  NULL,
	stato varchar(50) NOT NULL,
	fk_domanda_loculo int NOT NULL,
	fk_domanda_disposizione int NULL,
	fk_user_modifier int NULL,
	data_update date NULL,
	data_insert date NULL,
	FOREIGN key(fk_user_modifier) references users (id),
	FOREIGN key(fk_domanda_disposizione) references domanda (id),
	FOREIGN key(fk_domanda_disposizione) references domanda (id)
);

INSERT INTO contratto (id, protocollo,data_inizio,data_scadenza,stato,fk_domanda_loculo,fk_domanda_disposizione,fk_user_modifier,data_insert,data_update) VALUES
  (1,106,'2023-09-25','2058-09-25','in lavorazione',2,3,1, '2023-09-25','2023-09-25'),
  (2,405, '2023-03-4','2058-03-4','eseguito',1,2,2,'2023-03-04','2020-02-8'),
  (3, 450, '2023-04-8', '2058-04-8','sto elaborando',3,1,3, '2023-04-08','2023-04-8'); 
=======
INSERT INTO posto (Id, loculo,fornice) VALUES
  (1, 'first', 'last 1'),
  (2, 'first', 'last 2'),
  (3, 'first', 'last 3');
  
DROP TABLE IF EXISTS domanda;

CREATE TABLE domanda (
	id INT NOT NULL,
	protocollo int NOT NULL,
	data_domanda date NOT NULL,
	stato varchar,
	utenti varchar NOT NULL,
	data_insert date NOT NULL,
	data_update date NOT NULL,
	fk_contraente INT NOT NULL
	);

INSERT INTO domanda (id, protocollo, data_domanda,stato,utenti, data_insert, data_update, fk_contraente) VALUES
  (1, 55430, '2022-02-01',  'lavorazione','tommaso', '2022-01-08', '2022-01-08', 010),
  (2, 54789, '2022-02-05',  'lavorazione', 'tommaso', '2022-01-07', '2022-01-07', 020),
  (3, 42587, '2022-02-07',  'accolta', 'tommaso', '2022-01-01', '2022-01-01', 008);
>>>>>>> refs/heads/dev_2stefano
