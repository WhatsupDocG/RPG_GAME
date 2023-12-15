CREATE SEQUENCE enemy_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE rpg_enemy (
  id int4 NOT NULL DEFAULT nextval('enemy_id_seq'),
  name VARCHAR (255),
  damage float4,
  armor float4,
  enemy_level_id int4,
  enemy_type_id int4,
  PRIMARY KEY (id));

CREATE SEQUENCE enemy_level_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE rpg_enemy_level (
                          id int4 NOT NULL DEFAULT nextval('enemy_level_id_seq'),
                          name VARCHAR (255),
                          PRIMARY KEY (id));

CREATE SEQUENCE enemy_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE rpg_enemy_type (
                          id int4 NOT NULL DEFAULT nextval('enemy_type_id_seq'),
                          name VARCHAR (255),
                          PRIMARY KEY (id));

