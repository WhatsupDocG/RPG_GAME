CREATE SEQUENCE location_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE rpg_location (
  id int4 NOT NULL DEFAULT nextval('location_id_seq'),
  name VARCHAR (255),
  PRIMARY KEY (id));

