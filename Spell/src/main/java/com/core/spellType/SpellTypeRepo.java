package com.core.spellType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellTypeRepo extends JpaRepository<SpellType, Integer> {
}
