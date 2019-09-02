-- Titre             : Script SQL (PostgreSQL) de création de la base de données du projet bibliothèque
-- Version           : 1.0
-- Date création     : 07 mars 2006
-- Date modification : 22 octobre 2018
-- Auteur            : Kevin IBARRA, NGUYEN Van-Khoa, Philippe TANGUY
-- Description       : Ce script est une ébauche, à compléter, qui permet la création de la table
--                     "livre" pour la réalisation de la fonctionnalité "liste de tous les livres".
--creation_base_de_donnees.sql
drop table if exists "emprunt";
drop table if exists "exemplaire";
drop table if exists "livre";
drop table if exists "abonne";


-- +----------------------------------------------------------------------------------------------+
-- | Création des tables                                                                          |
-- +----------------------------------------------------------------------------------------------+

create table livre
(
  id     serial not null primary key,
  isbn10 varchar(25) unique,
  isbn13 varchar(25) unique,
  titre  varchar(50) not null,
  auteur varchar(30)
);

create table exemplaire
(
  id        serial not null primary key,
  id_livre  int not null,
  status    bool not null, --True signifie emprunt
  foreign key (id_livre) references livre(id)
);

create table abonne
(
  id     serial not null primary key,
  nom    varchar(25) not null,
  prenom varchar(25),
  statut varchar(20) not null,
  email  varchar(50)
);

create table emprunt
(
  id_abonne         int,
  id_exemplaire		int not null,	-- manque **
  date_emprunt      date,
  date_retour       date,
  constraint pk_emprunt primary key (id_exemplaire, date_emprunt),--id_abonne ne peut pas etre le cle primaire **
  foreign key (id_abonne) references abonne(id),
  foreign key (id_exemplaire) references exemplaire(id) -- manque **
);


-- +----------------------------------------------------------------------------------------------+
-- | Insertion de quelques données de pour les tests                                              |
-- +----------------------------------------------------------------------------------------------+

-- livre
insert into livre values(nextval('livre_id_seq'), '2-84177-042-7', NULL,                'JDBC et JAVA',                            'George REESE');    -- id = 1
insert into livre values(nextval('livre_id_seq'), NULL,            '978-2-7440-7222-2', 'Sociologie des organisations',            'Michel FOUDRIAT'); -- id = 2
insert into livre values(nextval('livre_id_seq'), '2-212-11600-4', '978-2-212-11600-7', 'Le data warehouse',                       'Ralph KIMBALL');   -- id = 3
insert into livre values(nextval('livre_id_seq'), '2-7117-4811-1', NULL,                'Entrepots de données',                    'Ralph KIMBALL');   -- id = 4
insert into livre values(nextval('livre_id_seq'), '2012250564',    '978-2012250567',    'Oui-Oui et le nouveau taxi',              'Enid BLYTON');     -- id = 5
insert into livre values(nextval('livre_id_seq'), '2203001011',    '978-2203001015',    'Tintin au Congo',                         'HERGÉ');           -- id = 6
insert into livre values(nextval('livre_id_seq'), '2012011373',    '978-2012011373',    'Le Club des Cinq et le trésor de l''île', 'Enid BLYTON');     -- id = 7
insert into livre values(nextval('livre_id_seq'), '2012011377',    '978-2012011375',    'Le Club des Cinq et le trésor de l''île2', 'Enid BLYTON');     -- id = 7

-- exemplaire
insert into exemplaire values(nextval('exemplaire_id_seq'),1,True);     -- id = 1
insert into exemplaire values(nextval('exemplaire_id_seq'),2,False);    -- id = 2
insert into exemplaire values(nextval('exemplaire_id_seq'),2,False);    -- id = 3
insert into exemplaire values(nextval('exemplaire_id_seq'),2,True);     -- id = 4
insert into exemplaire values(nextval('exemplaire_id_seq'),3,False);    -- id = 5
insert into exemplaire values(nextval('exemplaire_id_seq'),3,False);    -- id = 6
insert into exemplaire values(nextval('exemplaire_id_seq'),3,False);    -- id = 7
insert into exemplaire values(nextval('exemplaire_id_seq'),4,True);     -- id = 8
insert into exemplaire values(nextval('exemplaire_id_seq'),5,False);    -- id = 9
insert into exemplaire values(nextval('exemplaire_id_seq'),6,False);    -- id = 10
insert into exemplaire values(nextval('exemplaire_id_seq'),7,False);    -- id = 11
insert into exemplaire values(nextval('exemplaire_id_seq'),7,False);    -- id = 12
insert into exemplaire values(nextval('exemplaire_id_seq'),7,False);    -- id = 13
insert into exemplaire values(nextval('exemplaire_id_seq'),7,False);    -- id = 14

--abonne
insert into abonne values(nextval('abonne_id_seq'), 	'Nguyen', 		'Van-Khoa',         'Etudiant',            	'van-khoa@imt.com');    	-- id = 1
insert into abonne values(nextval('abonne_id_seq'), 	'Ducuara',      'Daniel', 			'Etudiant',            		'dducuara@ud.co'); 			-- id = 2
insert into abonne values(nextval('abonne_id_seq'), 	'Agudelo',		'Santiago', 		'Enseignant',                NULL);   					-- id = 3
insert into abonne values(nextval('abonne_id_seq'), 	'Ibarra', 		NULL,               'Enseignant',                'ksibarra@telecom.eu');   	-- id = 4
insert into abonne values(nextval('abonne_id_seq'), 	'Delaude',      'Luciano',    		'Etudiant',              'luciano@facebook.com');   -- id = 5

-- emprunt
insert into emprunt values(1,1,'2018-10-10',null);              	-- id = 1
insert into emprunt values(1,1,'2017-02-10','2018-10-10');               -- id = 2
insert into emprunt values(2,3,'2018-02-10','2018-10-10');     			-- id = 3
insert into emprunt values(3,1,'2017-08-10','2018-10-10');     			-- id = 4
insert into emprunt values(3,1,'2018-09-26','2018-10-10');             	-- id = 5
insert into emprunt values(4,4,'2016-02-10','2018-10-10');     			-- id = 6
insert into emprunt values(4,4,'2018-10-15',null);             	-- id = 7
insert into emprunt values(4,8,'2018-10-15',null);             	-- id = 8