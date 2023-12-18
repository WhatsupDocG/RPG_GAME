CREATE SEQUENCE character_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE rpg_character (
  id int4 NOT NULL DEFAULT nextval('character_id_seq'),
  name VARCHAR (255),
  sex int4,
  health float4,
  damage float4,
  character_level_id int4,
  location_id int4,
  PRIMARY KEY (id));

CREATE SEQUENCE character_level_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE rpg_character_level (
    id int4 NOT NULL DEFAULT nextval('character_level_id_seq'),
    name VARCHAR (255),
    PRIMARY KEY (id));
