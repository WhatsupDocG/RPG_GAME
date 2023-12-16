CREATE SEQUENCE item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE rpg_item (
    id int4 NOT NULL DEFAULT nextval('item_id_seq'),
    name VARCHAR (255),
    damage float4,
    armor float4,
    item_level_id int4,
    item_type_id int4,
    character_id int4,
    PRIMARY KEY (id));

CREATE SEQUENCE item_level_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE rpg_item_level (
    id int4 NOT NULL DEFAULT nextval('item_level_id_seq'),
    name VARCHAR (255),
    PRIMARY KEY (id));

CREATE SEQUENCE item_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE rpg_item_type (
    id int4 NOT NULL DEFAULT nextval('item_type_id_seq'),
    name VARCHAR (255),
    PRIMARY KEY (id));

