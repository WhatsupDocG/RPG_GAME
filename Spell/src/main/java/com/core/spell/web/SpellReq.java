package com.core.spell.web;

public class SpellReq {

    private String name;
    private float damage;
    private float heal;
    private Integer spellType;
    private Integer spellLevel;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getDamage() {
        return damage;
    }
    public void setDamage(float damage) {
        this.damage = damage;
    }

    public Integer getSpellType() {
        return spellType;
    }
    public void setSpellType(Integer spellType) {
        this.spellType = spellType;
    }

    public Integer getSpellLevel() {
        return spellLevel;
    }
    public void setSpellLevel(Integer spellLevel) {
        this.spellLevel = spellLevel;
    }

    public float getHeal() {
        return heal;
    }
    public void setHeal(float heal) {
        this.heal = heal;
    }
}
