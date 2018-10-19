--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- @author kvlinden
-- @version Summer, 2015
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS Winner;
DROP TABLE IF EXISTS PlayerProp;
DROP TABLE IF EXISTS PlayerGame;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS Property;
DROP TABLE IF EXISTS Game;


-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY,
	time timestamp
	);

CREATE TABLE Property (
	ID integer PRIMARY KEY,
	name varchar(50)
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY,
	name varchar(50),
	CASH integer,
	location integer
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES game(ID),
	playerID integer REFERENCES player(ID)
	);

CREATE TABLE PlayerProp (
	playerID integer REFERENCES playerID,
	proertyID integer REFERENCES proertyID,
	numHouses integer,
	numHotels integer
	);

CREATE TABLE Winner (
	winnerID integer REFERENCES Player(ID),
	gameID integer REFERENCES Game(ID),
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);


-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Property TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON PlayerProp TO PUBLIC;
GRANT SELECT ON Winner TO PUBLIC;


-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

INSERT INTO Winner(ID, emailAddress) VALUES (1, 'me@calvin.edu');
INSERT INTO Winner VALUES (2, 'king@gmail.edu', 'The King');
INSERT INTO Winner VALUES (3, 'dog@gmail.edu', 'Dogbreath');

INSERT INTO PlayerGame VALUES (1, 1, 0.00);
INSERT INTO PlayerGame VALUES (1, 2, 0.00);
INSERT INTO PlayerGame VALUES (1, 3, 2350.00);
INSERT INTO PlayerGame VALUES (2, 1, 1000.00);
INSERT INTO PlayerGame VALUES (2, 2, 0.00);
INSERT INTO PlayerGame VALUES (2, 3, 500.00);
INSERT INTO PlayerGame VALUES (3, 2, 0.00);
INSERT INTO PlayerGame VALUES (3, 3, 5500.00);
