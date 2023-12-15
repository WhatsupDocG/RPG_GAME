CREATE SEQUENCE spell_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE rpg_spell (
  id int4 NOT NULL DEFAULT nextval('spell_id_seq'),
  name VARCHAR (255),
  damage float4,
  heal float4,
  spell_level_id int4,
  spell_type_id int4,
  PRIMARY KEY (id));

CREATE SEQUENCE spell_level_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE rpg_spell_level (
                          id int4 NOT NULL DEFAULT nextval('spell_level_id_seq'),
                          name VARCHAR (255),
                          PRIMARY KEY (id));

CREATE SEQUENCE spell_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE rpg_spell_type (
                          id int4 NOT NULL DEFAULT nextval('spell_type_id_seq'),
                          name VARCHAR (255),
                          PRIMARY KEY (id));

