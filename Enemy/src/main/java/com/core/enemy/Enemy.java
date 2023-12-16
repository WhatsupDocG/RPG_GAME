package com.core.enemy;

import org.hibernate.annotations.GenericGenerator;
import com.core.EnemyLevel.*;
import com.core.EnemyType.*;
import javax.persistence.*;

@Entity
@Table(name = "rpg_enemy")
public class Enemy {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "enemy_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "enemy_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enemy_id_seq")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="health")
    private float health;
    @Column(name = "damage")
    private float damage;
    @ManyToOne
    private EnemyLevel enemyLevel;
    @ManyToOne
    private EnemyType enemyType;

    @Column(name = "location_id")
    private Integer locationId;

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

    public EnemyLevel getEnemyLevel() {
        return this.enemyLevel;
    }
    public void setEnemyLevel(EnemyLevel EnemyLevel) {
        this.enemyLevel = EnemyLevel;
    }

    public EnemyType getEnemyType() {
        return this.enemyType;
    }
    public void setEnemyType(EnemyType enemyType) {
        this.enemyType = enemyType;
    }

    public Integer getLocationId() {
        return locationId;
    }
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

}
