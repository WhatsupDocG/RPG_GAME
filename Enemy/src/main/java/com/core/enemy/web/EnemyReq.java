package com.core.enemy.web;

import com.core.EnemyType.EnemyType;

import javax.validation.constraints.NotEmpty;

public class EnemyReq {
    @NotEmpty
    private String name;
    private float health;
    private float damage;
    private Integer enemyLevel;
    private Integer enemyType;
    private Integer locationId;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getHealth() {
        return health;
    }
    public void setHealth(float health) {
        this.health = health;
    }

    public float getDamage() {
        return damage;
    }
    public void setDamage(float damage) {
        this.damage = damage;
    }

    public Integer getEnemyLevel() {
        return enemyLevel;
    }
    public void setEnemyLevel(Integer enemyLevel) {
        this.enemyLevel = enemyLevel;
    }

    public Integer getEnemyType() {
        return enemyType;
    }
    public void setEnemyType(Integer enemyType) {
        this.enemyType = enemyType;
    }

    public Integer getLocationId() {
        return locationId;
    }
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

}
