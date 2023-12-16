package com.core.EnemyLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyLevelRepo extends JpaRepository<EnemyLevel, Integer> {
}
