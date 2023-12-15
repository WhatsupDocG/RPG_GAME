package com.core.itemLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemLevelRepo extends JpaRepository<ItemLevel, Integer> {
}
