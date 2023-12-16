package com.core.spell.web;

public class SpellView {
    private Integer id;
    private String name;
    private float damage;
    private float heal;
    private Integer spellLevel;
    //private Integer spellType;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

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

    /*
    public Integer getSpellType() {
        return spellType;
    }
    public void setSpellType(Integer spellType) {
        this.spellType = spellType;
    }
*/

}
