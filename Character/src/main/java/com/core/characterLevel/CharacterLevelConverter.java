package com.core.characterLevel;

import com.core.characterLevel.web.CharacterLevelView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CharacterLevelConverter implements Converter<CharacterLevel, CharacterLevelView> {
    @Override
    public CharacterLevelView convert(@NonNull CharacterLevel characterLevel) {
        CharacterLevelView view = new CharacterLevelView();
        view.setId(characterLevel.getId());
        view.setName(characterLevel.getName());
        return view;
    }

}
