package com.core.characterLevel;

import com.core.character.Character;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rpg_item_level")
public class ItemLevel {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "item_level_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "item_level_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_level_id_seq")
    private Integer id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "itemLevel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Character> items = new ArrayList<>();

    public List<Character> getItems() {
        return items;
    }

    public void setItems(List<Character> items) {
        this.items = items;
    }

    public void addItem(Character character) {
        items.add(character);
        character.setItemLevel(this);
    }
    public void removeItem(Character character) {
        items.remove(character);
        character.setItemLevel(null);
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
