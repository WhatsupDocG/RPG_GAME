package com.core.characterLevel;

import com.core.characterLevel.web.CharacterLevelReq;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class CharacterLevelService {

    private final CharacterLevelRepo characterLevelRepo;
    private final MessageSource messageSource;

    public CharacterLevelService(CharacterLevelRepo characterLevelRepo,
                                 MessageSource messageSource){
        this.characterLevelRepo = characterLevelRepo;
        this.messageSource = messageSource;
    }

    public CharacterLevel getCharacterLevelById(Integer id){
        return characterLevelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.LevelWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<CharacterLevel> findAllCharacterLevel(Pageable pageable){
        return characterLevelRepo.findAll(pageable);
    }

    public CharacterLevel createCharacterLevel(CharacterLevelReq req) {
        CharacterLevel characterLevel = new CharacterLevel();
        this.prepareCharacterLevel(characterLevel,req);
        characterLevelRepo.save(characterLevel);
        return characterLevel;
    }

    @Transactional
    public void deleteCharacterLevel(Integer id) {
        try {
            characterLevelRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.CharacterLevelWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public CharacterLevel updateCharacterLevel(CharacterLevel characterLevel, CharacterLevelReq req){
        this.prepareCharacterLevel(characterLevel,req);
        characterLevelRepo.save(characterLevel);
        return characterLevel;
    }
    public void prepareCharacterLevel(CharacterLevel characterLevel, CharacterLevelReq characterLevelBaseReq){
        characterLevel.setName(characterLevelBaseReq.getName());
    }


}
