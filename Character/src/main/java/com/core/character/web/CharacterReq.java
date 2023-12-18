package com.core.character.web;

import com.core.character.Sex;
import com.core.characterLevel.CharacterLevel;
import com.core.characterLevel.CharacterLevelRepo;

import javax.validation.constraints.NotEmpty;

public class CharacterReq {

    private final CharacterLevelRepo characterLevelRepo;
    @NotEmpty
    private String name;
    @NotEmpty
    private Sex sex;
    private float health;
    private float damage;
    private Integer characterLevel;

    private Integer locationId;

    public CharacterReq(CharacterLevelRepo characterLevelRepo) {
        this.characterLevelRepo = characterLevelRepo;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }
    public void setSex(Sex sex) {
        this.sex = sex;
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

    public Integer getCharacterLevel() {
        return characterLevel;
    }
    public void setCharacterLevel(Integer characterLevel) {
        this.characterLevel = characterLevel;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}
