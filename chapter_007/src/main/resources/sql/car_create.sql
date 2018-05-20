CREATE TABLE transmissions (
  id          SERIAL PRIMARY KEY,
  description VARCHAR(256) NOT NULL
);
CREATE TABLE engines (
  id          SERIAL PRIMARY KEY,
  description VARCHAR(256) NOT NULL
);
CREATE TABLE gearbox (
  id          SERIAL PRIMARY KEY,
  description VARCHAR(256) NOT NULL
);
CREATE TABLE car (
  id              SERIAL PRIMARY KEY,
  id_transmission INTEGER NOT NULL REFERENCES transmissions (id),
  id_engine       INTEGER NOT NULL REFERENCES engines (id),
  id_gearbox      INTEGER NOT NULL REFERENCES gearbox (id),
  description     VARCHAR(256)
);