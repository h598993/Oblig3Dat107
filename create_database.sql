DROP SCHEMA IF EXISTS Oblig3 CASCADE;
CREATE SCHEMA Oblig3;
SET search_path TO Oblig3;
CREATE table ansatt
(
   brukerid SERIAL,
   brukernavn VARCHAR (5) UNIQUE NOT NULL,
   fornavn VARCHAR (50) NOT NULL,
   etternavn VARCHAR (50) NOT NULL,
   ansettelsesdato TIMESTAMP NOT NULL,
   stilling VARCHAR (50),
   maanedslonn DECIMAL,
	avdeling INTEGER,
   CONSTRAINT ansatt_pk PRIMARY KEY (brukerid)
);
INSERT INTO ansatt
(
   brukernavn,
   fornavn,
   etternavn,
   ansettelsesdato,
   stilling,
   maanedslonn,
	avdeling
)
VALUES
(
   'sl',
   'Sylvester',
   'Stalone',
   '2016-06-22 19:10:25-07',
   'Student',
   600000,
	1
),
(
   'jh',
   'Jørgen',
   'Hattemaker',
   '2016-06-22 19:10:25-07',
   'Hattemaker',
   700000,
	2
),(
   'sf',
   'Svampebob',
   'Firkant',
   '2016-06-22 19:10:25-07',
   'Student',
   600000,
	3
),(
   'kb',
   'Karius',
   'Baktus',
   '2016-06-22 19:10:25-07',
   'Student',
   600000,
	3
),(
   'pp',
   'Pelle',
   'Politi',
   '2016-06-22 19:10:25-07',
   'Student',
   600000,
	1
);

CREATE table avdeling(
	avdelingsId SERIAL,
	navn VARCHAR(50),
	leder INTEGER NOT NULL,
	CONSTRAINT avdeling_pk PRIMARY KEY (avdelingsId),
	CONSTRAINT avdeling_fk FOREIGN KEY (leder) REFERENCES ansatt(brukerid)
);

INSERT INTO avdeling(navn, leder)

VALUES
('Innkjøp',1),
('Salg',2),
('Markedsføring',3);

ALTER TABLE ansatt
ALTER COLUMN avdeling
SET NOT NULL;

ALTER TABLE ansatt 
ADD CONSTRAINT ansatt_fk FOREIGN KEY (avdeling) REFERENCES avdeling(avdelingsId);