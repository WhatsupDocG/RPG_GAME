package com.core.spellLevel;

import com.core.spellLevel.web.SpellLevelReq;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class SpellLevelService {

    private final SpellLevelRepo spellLevelRepo;
    private final MessageSource messageSource;

    public SpellLevelService(SpellLevelRepo spellLevelRepo,
                           MessageSource messageSource){
        this.spellLevelRepo = spellLevelRepo;
        this.messageSource = messageSource;
    }

    public SpellLevel getSpellLevelById(Integer id){
        return spellLevelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.SpellLevelWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<SpellLevel> findAllSpellLevel(Pageable pageable){
        return spellLevelRepo.findAll(pageable);
    }

    public SpellLevel createSpellLevel(SpellLevelReq req) {
        SpellLevel spellLevel = new SpellLevel();
        this.prepareSpellLevel(spellLevel,req);
        spellLevelRepo.save(spellLevel);
        return spellLevel;
    }

    @Transactional
    public void deleteSpellLevel(Integer id) {
        try {
            spellLevelRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.SpellLevelWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public SpellLevel updateSpellLevel(SpellLevel spellLevel, SpellLevelReq req){
        this.prepareSpellLevel(spellLevel,req);
        spellLevelRepo.save(spellLevel);
        return spellLevel;
    }

    public void prepareSpellLevel(SpellLevel spellLevel, SpellLevelReq spellLevelBaseReq){
        spellLevel.setName(spellLevelBaseReq.getName());
    }

}
