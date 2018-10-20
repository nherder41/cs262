--
-- This SQL script builds a monopoly database, deleting any pre-existing version.
--
-- @author kvlinden
-- @version Summer, 2015
--

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS PlayerProp;
DROP TABLE IF EXISTS PlayerGameState;
DROP TABLE IF EXISTS PlayerGame;
DROP TABLE IF EXISTS Player;
DROP TABLE IF EXISTS Game;


-- Create the schema.
CREATE TABLE Game (
	ID integer PRIMARY KEY,
	time timestamp
	);

CREATE TABLE Player (
	ID integer PRIMARY KEY,
	name varchar(50),
	emailAddress varchar(50) NOT NULL,
	);

CREATE TABLE PlayerGame (
	gameID integer REFERENCES game(ID),
	playerID integer REFERENCES player(ID)
	);

CREATE TABLE PlayerGameState (
	gameID integer REFERENCES Game(ID),
	playerID integer REFERENCES Player(ID),
	position integer DEFAULT 1 CHECK (position BETWEEN 1 AND 40),
	cash integer
	);

CREATE TABLE PlayerProp (
	gameID integer REFERENCES Property(ID),
	playerID integer REFERENCES Player(ID),
	houses integer DEFAULT 0 CHECK (housing BETWEEN 0 AND 5),
	property integer NOT NULL CHECK (property BETWEEN 1 AND 28)
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


INSERT INTO Player VALUEs (1, "Nate Herder", "100");
INSERT INTO Player VALUEs (2, "Jim Bob", "350");
INSERT INTO Player VALUEs (3, "Sally Mae", "25");
INSERT INTO Player VALUEs (4, "Jane Doe", "275");
INSERT INTO Player VALUEs (5, "Tom Hanks", "50");
INSERT INTO Player VALUEs (6, "Michael Jackson", "400");


INSERT INTO PlayerGame VALUES (1, 1);
INSERT INTO PlayerGame VALUES (1, 2);
INSERT INTO PlayerGame VALUES (1, 3);
INSERT INTO PlayerGame VALUES (2, 4);
INSERT INTO PlayerGame VALUES (2, 5);
INSERT INTO PlayerGame VALUES (2, 6);


INSERT INTO PlayerGameState VALUES (1, 1, 22, 225);
INSERT INTO PlayerGameState VALUES (1, 1, 37, 150);
INSERT INTO PlayerGameState VALUES (1, 2, 14, 260);
INSERT INTO PlayerGameState VALUES (1, 3, 9, 50);
INSERT INTO PlayerGameState VALUES (1, 3, 40, 300);
INSERT INTO PlayerGameState VALUES (2, 4, 22, 100);
INSERT INTO PlayerGameState VALUES (2, 4, 37, 150);
INSERT INTO PlayerGameState VALUES (2, 5, 14, 300);
INSERT INTO PlayerGameState VALUES (2, 5, 9, 190);
INSERT INTO PlayerGameState VALUES (2, 6, 40, 245);


INSERT INTO PlayerProp VALUES (1, 1, 3, 4);
INSERT INTO PlayerProp VALUES (1, 1, 3, 5);
INSERT INTO PlayerProp VALUES (1, 1, 3, 6);
INSERT INTO PlayerProp VALUES (2, 6, 2, 20);
INSERT INTO PlayerProp VALUES (2, 6, 2, 16);
