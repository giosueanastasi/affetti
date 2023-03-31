DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT PRIMARY KEY,
  FIRST_NAME VARCHAR(250) NOT NULL,
  LAST_NAME VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) NOT NULL
);

INSERT INTO users (ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES
  (1, 'first', 'last 1', 'abc1@gmail.com'),
  (2, 'first', 'last 2', 'abc2@gmail.com'),
  (3, 'first', 'last 3', 'abc3@gmail.com');
  
  
DROP TABLE IF EXISTS posto;

CREATE TABLE posto (
	id INT PRIMARY KEY,
	loculo VARCHAR(250) NOT NULL,
	fornice VARCHAR(250) NOT NULL
);

INSERT INTO posto (ID, LOCULO,fornice) VALUES
  (1, 'first', 'last 1'),
  (2, 'first', 'last 2'),
  (3, 'first', 'last 3');
  
DROP TABLE IF EXISTS domanda;

CREATE TABLE domanda (
	id serial4 NOT NULL,
	protocollo int4 NOT NULL,
	data_domanda date NOT NULL,
	stato varchar,
	utenti varchar NOT NULL,
	data_insert date NOT NULL,
	data_update date NOT NULL,
	fk_contraente serial4 NOT NULL,

INSERT INTO domanda (id, protocollo, data_domanda,stato,utenti, data_insert, data_update, fk_contraente) VALUES
  (1, 55430, 2022-02-01, 2022-01-08, 'lavorazione','tommaso', 2022-01-08, 2022-01-08, 010),
  (2, 54789, 2022-02-05, 2022-01-07, 'lavorazione', 'tommaso', 2022-01-07, 2022-01-07, 020),
  (3, 42587, 2022-02-07, 2022-01-01, 'accolta', 'tommaso', 2022-01-01, 2022-01-01, 008);