package com.core.spellType;

import com.core.spellType.web.SpellTypeReq;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class SpellTypeService {

    private final SpellTypeRepo spellTypeRepo;
    private final MessageSource messageSource;

    public SpellTypeService(SpellTypeRepo spellTypeRepo,
                            MessageSource messageSource){
        this.spellTypeRepo = spellTypeRepo;
        this.messageSource = messageSource;
    }

    public SpellType getSpellTypeById(Integer id){
        return spellTypeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.SpellTypeWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<SpellType> findAllSpellType(Pageable pageable){
        return spellTypeRepo.findAll(pageable);
    }

    public SpellType createSpellType(SpellTypeReq req) {
        SpellType spellType = new SpellType();
        this.prepareSpellType(spellType,req);
        spellTypeRepo.save(spellType);
        return spellType;
    }

    @Transactional
    public void deleteSpellType(Integer id) {
        try {
            spellTypeRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.SpellTypeWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public SpellType updateSpellType(SpellType spellType, SpellTypeReq req){
        this.prepareSpellType(spellType,req);
        spellTypeRepo.save(spellType);
        return spellType;
    }

    public void prepareSpellType(SpellType spellType, SpellTypeReq spellTypeBaseReq){
        spellType.setName(spellTypeBaseReq.getName());
    }

}
