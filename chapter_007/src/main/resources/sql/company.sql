-- we have two tables:

-- CREATE TABLE company
-- (
--   id integer NOT NULL,
--   name character varying,
--   CONSTRAINT company_pkey PRIMARY KEY (id)
-- );

-- CREATE TABLE person
-- (
--   id integer NOT NULL,
--   name character varying,
--   company_id integer,
--   CONSTRAINT person_pkey PRIMARY KEY (id)
-- );

-- Make a query that:
-- 1) Retrieve in a single query:
--  - names of all persons that are NOT in the company with id = 5
--  - company name for each person:
SELECT person.name AS person_name, company.name AS company_name
FROM person INNER JOIN company ON company_id = company.id WHERE company_id <> 5;


-- 2) Select the name of the company with the maximum number of persons + number of persons in this company
SELECT c.name AS company_name, COUNT(p.company_id) AS person_amount
FROM company AS c
       JOIN person AS p ON c.id = p.company_id GROUP BY c.name
ORDER BY person_amount DESC LIMIT 1;