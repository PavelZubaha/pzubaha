INSERT INTO engines(description) VALUES
  ('test1 engine'),
  ('test2 engine'),
  ('test3 engine');

INSERT INTO transmissions(description)
VALUES
  ('test1 transmission'),
  ('test2 transmission'),
  ('test3 transmission');

INSERT INTO gearbox(description)
VALUES
  ('test1 gearbox'),
  ('test2 gearbox'),
  ('test3 gearbox');

INSERT INTO car(description, id_engine, id_transmission, id_gearbox) VALUES
  ('Test1_car', 1, 2, 3),
  ('Test2_car', 2, 1, 1);

