package com.core.spell;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepo extends JpaRepository<Spell, Integer> {
}
