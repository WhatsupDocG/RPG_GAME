INSERT INTO rpg_spell (name, damage, heal, spell_level_id, spell_type_id, character_id)
VALUES
('sunshine', 0, 20, 1, 1, 1),
('sunshine', 0, 20, 1, 1, 2);

INSERT INTO rpg_spell_type (name)
VALUES
    ('heal');

INSERT INTO rpg_spell_level (name)
VALUES
    ('usual');