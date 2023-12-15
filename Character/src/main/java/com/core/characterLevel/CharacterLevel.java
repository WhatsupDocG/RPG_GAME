package com.core.characterLevel;

import com.core.character.Character;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rpg_character_level")
public class CharacterLevel {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "character_level_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "character_level_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_level_id_seq")
    private Integer id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "characterLevel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Character> characters = new ArrayList<>();
    
    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public void addCharacter(Character character) {
        characters.add(character);
        character.setCharacterLevel(this);
    }
    public void removeCharacter(Character character) {
        characters.remove(character);
        character.setCharacterLevel(null);
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
