-- create database
CREATE DATABASE item_tracker;

-- create tables structure
CREATE TABLE rules (
  rule_id SERIAL PRIMARY KEY,
  rule_desc TEXT NOT NULL
);
CREATE TABLE roles (
  role_id SERIAL PRIMARY KEY,
  role_desc TEXT NOT NULL
);
CREATE TABLE rules_to_role (
  role_id INTEGER NOT NULL REFERENCES roles,
  rule_id INTEGER NOT NULL REFERENCES rules,
  PRIMARY KEY(role_id, rule_id)
);
CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  user_name VARCHAR(128) NOT NULL,
  user_email VARCHAR(128) NOT NULL UNIQUE,
  user_password VARCHAR(128) NOT NULL,
  user_registration  TIMESTAMP NOT NULL DEFAULT NOW(),
  role_id INTEGER NOT NULL REFERENCES roles DEFAULT 1
);
CREATE TABLE category (
  cat_id SERIAL PRIMARY KEY,
  cat_name VARCHAR(128) NOT NULL UNIQUE
);
CREATE TABLE statuses (
  stat_id  SERIAL PRIMARY KEY,
  stat_name VARCHAR(64) NOT NULL UNIQUE,
  stat_desc VARCHAR(256) NOT NULL
);
CREATE TABLE items (
  item_id SERIAL PRIMARY KEY,
  item_desc TEXT NOT NULL,
  item_date TIMESTAMP NOT NULL DEFAULT NOW(),
  stat_id INTEGER NOT NULL REFERENCES statuses,
  cat_id INTEGER NOT NULL REFERENCES category,
  user_id INTEGER NOT NULL REFERENCES users
);
CREATE TABLE attaches (
  file_id SERIAL PRIMARY KEY,
  file_path VARCHAR(256),
  file_name VARCHAR(256),
  item_id INTEGER NOT NULL REFERENCES items
);
CREATE TABLE comments (
  comment_id SERIAL PRIMARY KEY,
  comment_body TEXT,
  item_id INTEGER NOT NULL REFERENCES items
);

-- INSERT some data

INSERT INTO category(cat_name) VALUES ('test_category');
INSERT INTO statuses(stat_name, stat_desc) VALUES ('test_status_name', 'this_status_mean_that');
INSERT INTO roles(role_desc) VALUES ('default_role');
INSERT INTO rules(rule_desc) VALUES ('test_rule');
INSERT INTO rules_to_role(role_id, rule_id) VALUES (1, 1);
INSERT INTO users(user_name, user_email, user_password, role_id) VALUES (
  'test_user_1', 'test1@test.com', 'test_password1', 1),
  ('test_user_2', 'test2@test.com', 'test_password2', 1),
  ('test_user_3', 'test3@test.com', 'test_password3', 1);

INSERT INTO items(item_desc, stat_id, cat_id, user_id) VALUES (
  'first_item', 1, 1, 1),
  ('second_item', 1, 1, 2);

