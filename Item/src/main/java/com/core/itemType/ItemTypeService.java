package com.core.itemType;

import com.core.itemType.web.ItemTypeReq;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class ItemTypeService {

    private final ItemTypeRepo itemTypeRepo;
    private final MessageSource messageSource;

    public ItemTypeService(ItemTypeRepo itemTypeRepo,
                            MessageSource messageSource){
        this.itemTypeRepo = itemTypeRepo;
        this.messageSource = messageSource;
    }

    public ItemType getItemTypeById(Integer id){
        return itemTypeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.ItemTypeWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<ItemType> findAllItemType(Pageable pageable){
        return itemTypeRepo.findAll(pageable);
    }

    public ItemType createItemType(ItemTypeReq req) {
        ItemType itemType = new ItemType();
        this.prepareItemType(itemType,req);
        itemTypeRepo.save(itemType);
        return itemType;
    }

    @Transactional
    public void deleteItemType(Integer id) {
        try {
            itemTypeRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.ItemTypeWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public ItemType updateItemType(ItemType itemType, ItemTypeReq req){
        this.prepareItemType(itemType,req);
        itemTypeRepo.save(itemType);
        return itemType;
    }
    public void prepareItemType(ItemType itemType, ItemTypeReq itemTypeBaseReq){
        itemType.setName(itemTypeBaseReq.getName());
    }


}
