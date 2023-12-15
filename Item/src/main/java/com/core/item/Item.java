package com.core.item;

import com.core.itemLevel.ItemLevel;
import com.core.itemType.ItemType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "rpg_item")
public class Item {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "item_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "item_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    private Integer id;
    @Column(name="name")
    private String name;

    @Column(name = "damage")
    private float damage;

    @Column(name = "armor")
    private float armor;

    @ManyToOne
    private ItemLevel itemLevel;

    @ManyToOne
    private ItemType itemType;

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

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public ItemLevel getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(ItemLevel itemLevel) {
        this.itemLevel = itemLevel;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
