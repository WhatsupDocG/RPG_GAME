package com.core.character;

import com.core.characterLevel.CharacterLevel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "rpg_character")
public class Character {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "character_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "character_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_id_seq")
    private Integer id;
    @Column(name="name")
    private String name;

    @Column(name="sex")
    private Sex sex;

    @Column(name="health")
    private float health;

    @Column(name = "damage")
    private float damage;

    @Column(name = "location_id")
    private Integer locationId;

    @ManyToOne
    private CharacterLevel characterLevel;

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

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public CharacterLevel getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(CharacterLevel characterLevel) {
        this.characterLevel = characterLevel;
    }
}
