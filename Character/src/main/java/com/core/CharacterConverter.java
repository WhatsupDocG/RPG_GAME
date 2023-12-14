package com.core;

import com.core.web.CharacterView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CharacterConverter implements Converter<Character, CharacterView> {
    @Override
    public CharacterView convert(@NonNull Character character) {
        CharacterView view = new CharacterView();
        view.setId(character.getId());
        view.setSex(character.getSex());
        view.setName(character.getName());
        view.setDamage(character.getDamage());
        view.setHealth(character.getHealth());
        return view;
    }

}
