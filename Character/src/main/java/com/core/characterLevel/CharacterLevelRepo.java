package com.core.characterLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterLevelRepo extends JpaRepository<CharacterLevel, Integer> {
}
