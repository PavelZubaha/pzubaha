-- Вывести все машины
SELECT c.id, c.description, t.description, g.description, e.description FROM car AS c
  INNER JOIN transmissions AS t ON c.id_transmission = t.id
  INNER JOIN gearbox AS g ON c.id_gearbox = g.id
  INNER JOIN engines AS e ON c.id_engine = e.id;


-- Вывести все неиспользуемые детали
SELECT t.id, t.description FROM car AS c
  RIGHT JOIN transmissions AS t ON c.id_transmission = t.id WHERE c.id_transmission IS NULL;

SELECT g.id, g.description FROM car AS c
  RIGHT JOIN gearbox AS g ON c.id_transmission = g.id WHERE c.id_transmission IS NULL;

SELECT e.id, e.description FROM car AS c
  RIGHT JOIN engines AS e ON c.id_transmission = e.id WHERE c.id_transmission IS NULL;
