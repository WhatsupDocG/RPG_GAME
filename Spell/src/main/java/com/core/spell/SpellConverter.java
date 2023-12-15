package com.core.spell;

import com.core.spell.web.SpellView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SpellConverter implements Converter<Spell, SpellView> {
    @Override
    public SpellView convert(@NonNull Spell spell) {
        SpellView view = new SpellView();
        view.setId(spell.getId());
        view.setName(spell.getName());
        view.setDamage(spell.getDamage());
        view.setHeal(spell.getHeal());
        view.setSpellLevel(spell.getSpellLevel().getId());
        view.setSpellType(spell.getSpellType().getId());
        return view;
    }

}
