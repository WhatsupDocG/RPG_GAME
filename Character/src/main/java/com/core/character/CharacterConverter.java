package com.core.character;

import com.core.character.web.CharacterController;
import com.core.character.web.CharacterView;
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
        view.setCharacterLevel(character.getCharacterLevel().getName());
        return view;
    }

}
