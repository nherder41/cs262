-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- @author kvlinden
-- @version Summer, 2015
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS PlayerGame CASCADE;
DROP TABLE IF EXISTS Game CASCADE;
DROP TABLE IF EXISTS Player CASCADE;
DROP TABLE IF EXISTS PlayerProp CASCADE;
DROP TABLE IF EXISTS PlayerGameState CASCADE;  --PSQL complained an said to add CASCADE...

-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY,
	time timestamp
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY,
	emailAddress varchar(50) NOT NULL,
	name varchar(50)
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID)
	);

CREATE TABLE PlayerGameState (
        gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID),
	cash integer DEFAULT 0,
	boardPosition integer DEFAULT 1 CHECK (boardPosition BETWEEN 1 AND 40)
        );

CREATE TABLE PlayerProp (
        gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID),
	property integer NOT NULL CHECK (property BETWEEN 1 AND 28),
	housing integer DEFAULT 0 CHECK (housing BETWEEN 0 AND 5)
        );

-- Allow users to select data from the tables.
GRANT SELECT ON Game TO PUBLIC;
GRANT SELECT ON Player TO PUBLIC;
GRANT SELECT ON PlayerGame TO PUBLIC;
GRANT SELECT ON PlayerGameState TO PUBLIC;
GRANT SELECT ON PlayerProp TO PUBLIC;

-- Add sample records.
INSERT INTO Game VALUES (1, '2006-06-27 08:00:00');
INSERT INTO Game VALUES (2, '2006-06-28 13:20:00');
INSERT INTO Game VALUES (3, '2006-06-29 18:41:00');

INSERT INTO Player(ID, emailAddress) VALUES (1, 'nateherder@gmail.com');
INSERT INTO Player VALUES (2, 'ripElvis@gmail.edu', 'Elvis Presely');
INSERT INTO Player VALUES (3, 'mj@gmail.edu', 'Michael Jackson');

INSERT INTO PlayerGame VALUES (1, 1);
INSERT INTO PlayerGame VALUES (1, 2);
INSERT INTO PlayerGame VALUES (1, 3);
INSERT INTO PlayerGame VALUES (2, 1);
INSERT INTO PlayerGame VALUES (2, 2);
INSERT INTO PlayerGame VALUES (2, 3);
INSERT INTO PlayerGame VALUES (3, 2);
INSERT INTO PlayerGame VALUES (3, 3);

-- Add sample active game records
INSERT INTO PlayerGameState VALUES (4, 1, 100, 30);
INSERT INTO PlayerGameState VALUES (4, 2, 30, 2);

-- Add properties for active games
INSERT INTO PlayerProp VALUES (4, 1, 21, 3);
INSERT INTO PlayerProp VALUES (4, 1, 18, 2);
INSERT INTO PlayerProp VALUES (4, 2, 4, 5);