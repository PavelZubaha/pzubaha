-- В системе заданы таблицы
-- product(id, name, type_id, expired_date, price)
--# type(id, name)

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT
  p.id,
  p.name,
  t.name
FROM product AS p INNER JOIN type AS t
    ON p.type_id = t.id AND t.name = 'СЫР';
-- select p.id, p.name, t.name from product as p, type as t where p.type_id = t.id and t.name = 'СЫР';

-- 2. Написать запрос на получение всех продуктов где в имени есть "мороженное"
SELECT *
FROM product
WHERE name LIKE '%мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT *
FROM product
WHERE EXTRACT(MONTH FROM expired_data) = EXTRACT(MONTH FROM now()) + 1;

-- 4. Написать запрос, который вывод самый дорогой продукт.
SELECT *
FROM product
WHERE price = (SELECT MAX(price)
               FROM product);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
-- SELECT type.id, type.name, COUNT(product.type_id) AS quantity FROM type 
-- LEFT JOIN product ON product.type_id = type.id
-- GROUP BY type.id;

SELECT
  t.*,
  (SELECT COUNT(p.id)
   FROM product AS p
   WHERE p.type_id = t.id)
FROM type AS t;

-- 6.писать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT
  p.id,
  p.name,
  t.name
FROM product AS p
  INNER JOIN type AS t
    ON t.name
       IN ('СЫР', 'МОЛОКО')
       AND p.type_id = t.id;

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT *
FROM type
WHERE ((SELECT COUNT(product.id)
        FROM product
        WHERE product.type_id = type.id) < 10);

-- 8. Вывести все продукты и их тип.
SELECT
  p.name,
  t.name
FROM product AS p INNER JOIN type AS t
    ON p.type_id = t.id;



