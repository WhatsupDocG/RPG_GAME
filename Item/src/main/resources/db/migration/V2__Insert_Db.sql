INSERT INTO rpg_item (name, damage,armor,item_level_id,item_type_id, character_id)
VALUES
('sword', 30,0,1,1, 1),
('axe', 30,0,1,1, 2);

INSERT INTO rpg_item_type (name)
VALUES
    ('weapon');

INSERT INTO rpg_item_level (name)
VALUES
    ('usual');