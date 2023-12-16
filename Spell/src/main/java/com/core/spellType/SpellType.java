package com.core.spellType;

import com.core.spell.Spell;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rpg_spell_type")
public class SpellType {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "spell_type_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "spell_type_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spell_type_id_seq")
    private Integer id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "spellType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Spell> spells = new ArrayList<>();

    public List<Spell> getSpells() {
        return spells;
    }
    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public void addSpell(Spell spell) {
        spells.add(spell);
        spell.setSpellType(this);
    }
    public void removeSpell(Spell spell) {
        spells.remove(spell);
        spell.setSpellType(null);
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
