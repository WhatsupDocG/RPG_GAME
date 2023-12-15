package com.core.spellLevel;

import com.core.spellLevel.web.SpellLevelView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SpellLevelConverter implements Converter<SpellLevel, SpellLevelView> {
    @Override
    public SpellLevelView convert(@NonNull SpellLevel spellLevel) {
        SpellLevelView view = new SpellLevelView();
        view.setId(spellLevel.getId());
        view.setName(spellLevel.getName());
        return view;
    }

}
