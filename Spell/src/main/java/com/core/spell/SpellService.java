package com.core.spell;

import com.core.spell.web.SpellReq;
import com.core.spellLevel.SpellLevelRepo;
import com.core.spellType.SpellTypeRepo;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class SpellService {

    private final SpellRepo spellRepo;
    private final SpellTypeRepo spellTypeRepo;
    private final SpellLevelRepo spellLevelRepo;
    private final MessageSource messageSource;

    public SpellService(SpellRepo spellRepo,
                            MessageSource messageSource,
                       SpellLevelRepo spellLevelRepo,
                       SpellTypeRepo spellTypeRepo){
        this.spellRepo = spellRepo;
        this.messageSource = messageSource;
        this.spellLevelRepo = spellLevelRepo;
        this.spellTypeRepo = spellTypeRepo;
    }

    public Spell getSpellById(Integer id){
        return spellRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.SpellWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<Spell> findAllSpell(Pageable pageable){
        return spellRepo.findAll(pageable);
    }

    public Spell createSpell(SpellReq req) {
        Spell spell = new Spell();
        this.prepareSpell(spell,req);
        spellRepo.save(spell);
        return spell;
    }

    @Transactional
    public void deleteSpell(Integer id) {
        try {
            spellRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.SpellWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public Spell updateSpell(Spell spell, SpellReq req){
        this.prepareSpell(spell,req);
        spellRepo.save(spell);
        return spell;
    }
    public void prepareSpell(Spell spell, SpellReq spellBaseReq){
        spell.setName(spellBaseReq.getName());
        spell.setDamage(spellBaseReq.getDamage());
        spell.setHeal(spellBaseReq.getHeal());
        spell.setSpellLevel(spellLevelRepo.getOne(spellBaseReq.getSpellLevel()));
        spell.setSpellType(spellTypeRepo.getOne(spellBaseReq.getSpellType()));
    }
}
