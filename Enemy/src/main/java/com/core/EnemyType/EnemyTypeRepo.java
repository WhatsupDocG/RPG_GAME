package com.core.EnemyType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnemyTypeRepo extends JpaRepository<EnemyType, Integer> {
}
