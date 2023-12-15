package com.core.character.web;

import com.core.character.Sex;

import javax.validation.constraints.NotEmpty;

public class CharacterReq {
    @NotEmpty
    private String name;
    @NotEmpty
    private Sex sex;

    private float health;

    private float damage;

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
}
