DROP TABLE IF EXISTS comuni;

CREATE TABLE comuni(
	id int NOT NULL IDENTITY,
	nome varchar(100) NOT NULL,
    COD_COMUNE_ISTAT varchar(6) NOT NULL,
    COD_CATAST_COMUNE varchar(4) NOT NULL,
    provincia varchar(2) NOT NULL,
    COD_PROV varchar(3) NOT NULL,
    DESC_PROV varchar(50) NOT NULL,
    COD_REG varchar(2) NOT NULL,
    DESC_REG varchar(50),
	PRIMARY KEY (id)
);

	INSERT INTO comuni ( nome, COD_COMUNE_ISTAT,  COD_CATAST_COMUNE, provincia, COD_PROV, DESC_PROV, COD_REG, DESC_REG) VALUES
(' GIULIANOVA','67025','E058','TE','67','TERAMO','13','ABRUZZO ' ),
('SANT''EGIDIO ALLA VIBRATA','67038','I318','TE','67','TERAMO','13','ABRUZZO ' ),
('ASCOLI PICENO','44007','A462','AP','44','ASCOLI PICENO','11','MARCHE ' ),
('SANT''OMERO','67039','I348','TE','67','TERAMO','13','ABRUZZO ' ),
 ('AGRIGENTO','84001','A089','AG','84','AGRIGENTO','19','SICILIA ' ),
(' ARAGONA','84003','A351','AG','84','AGRIGENTO','19','SICILIA ' ),
(' BIVONA','84004','A896','AG','84','AGRIGENTO','19','SICILIA ' ),
(' CALAMONACI','84006','B377','AG','84','AGRIGENTO','19','SICILIA ' ),
(' CALTABELLOTTA','84007','B427','AG','84','AGRIGENTO','19','SICILIA ' );



	
  
  DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT NOT NULL IDENTITY,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(50) NOT NULL,
  ruolo VARCHAR(10) NOT NULL,
  fk_comune int NOT NULL,
  PRIMARY KEY (id),
  foreign key (fk_comune) references comuni (id)
);

INSERT INTO users ( username,password,ruolo,fk_comune) VALUES
  (' Antonio90','dcunwencwebci', 'utente',2),
  ( 'Stefano24', 'qecwebckw','utente',1),
  ( 'Giovanna98', 'ugcywie', 'utente',3); 
  
  
DROP TABLE IF EXISTS contratti; 
DROP TABLE IF EXISTS domande;
DROP TABLE IF EXISTS posti;

CREATE TABLE posti (
	id INT NOT NULL IDENTITY,
	loculo VARCHAR(10) NOT NULL,
	fornice VARCHAR(10) NOT NULL,
	tipo VARCHAR (15) ,
	stato VARCHAR(15) NOT NULL,
	data_update date NULL,
	data_insert date NULL,
	fk_user_modifier  int NULL,
	PRIMARY KEY (id),
	FOREIGN key(fk_user_modifier ) references users (id)
);


 INSERT INTO posti ( loculo,fornice,tipo,stato,data_update,data_insert,fk_user_modifier) VALUES
  ( 15, 150, 'intermedia','LIBERO' ,'2023-09-25','2023-09-25',1),
  ( 1, 8, 'monumentale','PRENOTATO' ,'2023-03-04','2020-02-8',2),
  ( 14, 150, 'intermedia','DA_LIBERARE' ,'2023-09-25','2023-09-25',1),
  ( 8,120, 'nuova', 'OCCUPATO','2023-04-8','2023-04-8',3); 
  
	DROP TABLE IF EXISTS assegnatari;

	CREATE TABLE assegnatari (
	id int NOT NULL IDENTITY,
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

INSERT INTO assegnatari ( nome,cognome,data_decesso,comune_decesso,data_update,data_insert,fk_user_modifier ) VALUES
  ( 'Stefano', 'Rossi','2023-09-20','Sant Egidio alla Vibrata', '2023-09-20','2023-09-25',1),
  ( 'Vincenzo','D Auri', '2020-02-4','Ascoli Piceno','2023-03-5','2020-02-8',3),
  ( 'Samuel','Feliciani', '2023-04-5','Giulianova', '2023-04-8','2023-04-8',2); 
	

DROP TABLE IF EXISTS contraenti;

CREATE TABLE contraenti (
	id int NOT NULL IDENTITY,
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

INSERT INTO contraenti ( nome,cognome,comune_nascita,provincia_nascita,stato_nascita,data_nascita,comune_residenza,provincia_residenza,via_residenza,civico_residenza,cap_residenza,telefono,codice_fiscale,email,note,fk_user_modifier ,data_insert,data_update) VALUES
  ('Antonio','Rossi', 'Sant Omero','TE','Italia','1998-09-20','Sant Egidio alla Vibrata','TE','Via Vibrata',27,64016,0861840071,'BNDNTN98P20I348D','rossiantonio@libero.it','',2,'2023-09-25','2023-09-25'),
  ( 'Antonietta', 'Di Giuseppe','Ascoli Piceno','AP','Italia','1990-08-15','Monticelli','AP','Via dei Sanniti',3,63100,3277665754,'DGPANT90P15L387J','','',1,'2023-03-4','2020-02-8'),
  ( 'Giovanna','Di Saverio','Giulianova','TE','Italia','1970-01-31','Giulianova','TE','Via Liguria',2,64021,3475700123,'DSRGVN70P31I351S','','',3,'2023-04-8','2023-04-8'); 



CREATE TABLE domande (
	id int NOT NULL IDENTITY,
	protocollo varchar(10) NOT NULL,
	data_protocollo date NOT NULL,
	stato varchar(20) NOT NULL,
	tipologia varchar(25) NOT NULL,
	fk_posto int NOT NULL,
	fk_assegnatario int NOT NULL,
	fk_contraente int NOT NULL,
	fk_user_modifier  int NULL,
	data_insert date NULL,
	data_update date NULL,
	PRIMARY KEY (id),
	FOREIGN key(fk_user_modifier ) references users (id),
	FOREIGN key(fk_assegnatario) references assegnatari (id),
	FOREIGN key(fk_posto) references posti (id),
	FOREIGN key(fk_contraente) references contraenti (id)
	);
	
  INSERT INTO domande (protocollo,data_protocollo,stato, tipologia,fk_posto,fk_assegnatario,fk_contraente,fk_user_modifier ,data_insert,data_update) VALUES
  (506,'2023-09-25','APERTA','LOCULO',2,1,3,2, '2023-09-25','2023-09-25'),
  (204, '2023-03-04','CHIUSA','TENUTA_DISPOSIZIONE',1,2,1,3,'2023-03-04','2020-02-8'),
  (890, '2023-04-8', 'APERTA','LOCULO',3,3,2,1, '2023-04-8','2023-04-8'); 
  
	
	
	CREATE TABLE contratti (
	id int NOT NULL IDENTITY,
	protocollo varchar(256) NULL,
	data_inizio date NOT NULL,
	data_scadenza date  NULL,
	stato varchar(50) NOT NULL,
	fk_domanda int NULL,
	fk_user_modifier int NULL,
	data_update date NULL,
	data_insert date NULL,
	FOREIGN key(fk_user_modifier) references users (id),
	FOREIGN key(fk_domanda) references domande (id)
);

INSERT INTO contratti ( protocollo,data_inizio,data_scadenza,stato,fk_domanda,fk_user_modifier,data_insert,data_update) VALUES
  (106,'2023-09-25','2058-09-25','IN_ATTESA_PAGAMENTO',3,1, '2023-09-25','2023-09-25'),
  (405, '2023-03-4','2058-03-4','PAGATO',2,2,'2023-03-04','2020-02-8'),
  ( 450, '2023-04-8', '2058-04-8','IN_ATTESA_PAGAMENTO',1,3, '2023-04-08','2023-04-8'); 
