package com.core.character;

import com.core.character.web.CharacterReq;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class CharacterService {

    private final CharacterRepo characterRepo;
    private final MessageSource messageSource;

    public CharacterService(CharacterRepo characterRepo,
                            MessageSource messageSource){
        this.characterRepo = characterRepo;
        this.messageSource = messageSource;
    }

    public Character getCharacterById(Integer id){
        return characterRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.CharacterWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<Character> findAllCharacter(Pageable pageable){
        return characterRepo.findAll(pageable);
    }

    public Character createCharacter(CharacterReq req) {
        Character character = new Character();
        this.prepareCharacter(character,req);
        characterRepo.save(character);
        return character;
    }

    @Transactional
    public void deleteCharacter(Integer id) {
        try {
            characterRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.CharacterWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public Character updateCharacter(Character character, CharacterReq req){
        this.prepareCharacter(character,req);
        characterRepo.save(character);
        return character;
    }
    public void prepareCharacter(Character character, CharacterReq characterBaseReq){
        character.setName(characterBaseReq.getName());
        character.setSex(characterBaseReq.getSex());
        character.setDamage(characterBaseReq.getDamage());
        character.setHealth(characterBaseReq.getHealth());
    }


}
