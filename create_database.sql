DROP SCHEMA IF EXISTS Oblig3 CASCADE;
CREATE SCHEMA Oblig3;
SET search_path TO Oblig3;
CREATE table ansatt
(
   brukerid SERIAL,
   brukernavn VARCHAR (5) UNIQUE,
   fornavn VARCHAR (50) NOT NULL,
   etternavn VARCHAR (50) NOT NULL,
   ansettelsesdato TIMESTAMP NOT NULL,
   stilling VARCHAR (50),
   maanedslonn DECIMAL,
   CONSTRAINT ansatt_pk PRIMARY KEY (brukerid)
);
INSERT INTO ansatt
(
   brukernavn,
   fornavn,
   etternavn,
   ansettelsesdato,
   stilling,
   maanedslonn
)
VALUES
(
   'sl',
   'Stian',
   'Lodemel',
   '2016-06-22 19:10:25-07',
   'Student',
   600000
),
(
   'jh',
   'Jørgen',
   'Hattemaker',
   '2016-06-22 19:10:25-07',
   'Hattemaker',
   700000
);