package com.core.spell;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellRepo extends JpaRepository<Spell, Integer> {
    List<Spell> findSpellsByCharacterId(Integer characterId);
}
