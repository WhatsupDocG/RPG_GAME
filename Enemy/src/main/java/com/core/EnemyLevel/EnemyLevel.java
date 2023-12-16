package com.core.EnemyLevel;

import com.core.enemy.Enemy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rpg_enemy_level")
public class EnemyLevel {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "enemy_level_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "enemy_level_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enemy_level_id_seq")
    private Integer id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "enemyLevel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enemy> enemies = new ArrayList<>();

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        enemy.setEnemyLevel(this);
    }
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        enemy.setEnemyLevel(null);
    }

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
}
