package com.core.spell;

import com.core.spellLevel.SpellLevel;
import com.core.spellType.SpellType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "rpg_spell")
public class Spell {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "spell_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "spell_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spell_id_seq")
    private Integer id;
    @Column(name="name")
    private String name;

    @Column(name = "damage")
    private float damage;

    @Column(name = "heal")
    private float heal;

    @ManyToOne
    private SpellLevel spellLevel;

    @ManyToOne
    private SpellType spellType;

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

    public float getHeal() {
        return heal;
    }

    public void setHeal(float heal) {
        this.heal = heal;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public SpellLevel getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(SpellLevel spellLevel) {
        this.spellLevel = spellLevel;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public void setSpellType(SpellType spellType) {
        this.spellType = spellType;
    }
}
