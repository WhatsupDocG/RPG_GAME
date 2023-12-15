package com.core.spellLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellLevelRepo extends JpaRepository<SpellLevel, Integer> {
}
