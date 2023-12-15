package com.core.itemLevel;

import com.core.itemLevel.web.ItemLevelReq;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class ItemLevelService {

    private final ItemLevelRepo itemLevelRepo;
    private final MessageSource messageSource;

    public ItemLevelService(ItemLevelRepo itemLevelRepo,
                           MessageSource messageSource){
        this.itemLevelRepo = itemLevelRepo;
        this.messageSource = messageSource;
    }

    public ItemLevel getItemLevelById(Integer id){
        return itemLevelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.ItemLevelWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<ItemLevel> findAllItemLevel(Pageable pageable){
        return itemLevelRepo.findAll(pageable);
    }

    public ItemLevel createItemLevel(ItemLevelReq req) {
        ItemLevel itemLevel = new ItemLevel();
        this.prepareItemLevel(itemLevel,req);
        itemLevelRepo.save(itemLevel);
        return itemLevel;
    }

    @Transactional
    public void deleteItemLevel(Integer id) {
        try {
            itemLevelRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.ItemLevelWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public ItemLevel updateItemLevel(ItemLevel itemLevel, ItemLevelReq req){
        this.prepareItemLevel(itemLevel,req);
        itemLevelRepo.save(itemLevel);
        return itemLevel;
    }
    public void prepareItemLevel(ItemLevel itemLevel, ItemLevelReq itemLevelBaseReq){
        itemLevel.setName(itemLevelBaseReq.getName());
    }


}
