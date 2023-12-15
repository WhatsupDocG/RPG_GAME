package com.core.spellType;

import com.core.spellType.web.SpellTypeView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SpellTypeConverter implements Converter<SpellType, SpellTypeView> {
    @Override
    public SpellTypeView convert(@NonNull SpellType spellType) {
        SpellTypeView view = new SpellTypeView();
        view.setId(spellType.getId());
        view.setName(spellType.getName());
        return view;
    }

}
