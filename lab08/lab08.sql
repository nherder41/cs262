--lab08 Queries:

--8.1

SELECT * FROM Game ORDER BY time DESC;

SELECT * FROM Game WHERE time > DATEADD(day, -7, GETDATE());

SELECT * FROM Player WHERE name IS NOT NULL;

Select PlayerID From PlayerGame Where score > 2000;

SELECT * FROM Player WHERE emailAddress LIKE '%gmail%';

--8.2

--a
SELECT score FROM PlayerGame pg WHERE pg.playerID = (SELECT ID FROM Player WHERE name = 'The King');

--b
SELECT name FROM Player WHERE ID = (SELECT TOP(1) playerID FROM PlayerGame pg
JOIN Game ga ON pg.gameID = ga.ID
WHERE time = '2006-06-28 13:20:00'
ORDER BY score DESC);

--c
-- This is a self join will allow you to compare data within a table without sorting using or ORDER BY

--d
--If database objects fit more than one role, a self join can be used to link them.
--A realistic example is a manager who is also an employee.  They would have employees under them and a manager.