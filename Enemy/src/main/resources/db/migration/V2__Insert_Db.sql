INSERT INTO rpg_enemy (name, health, damage, enemy_level_id, enemy_type_id, location_id)
VALUES
('Spider', 40, 20, 1, 1, 1),
('Orc', 70, 30, 2, 2, 2);

INSERT INTO rpg_enemy_type (name)
VALUES
    ('spiders'),
    ('orcs');

INSERT INTO rpg_enemy_level (name)
VALUES
    ('usual'),
    ('boss');