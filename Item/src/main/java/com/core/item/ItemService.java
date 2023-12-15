package com.core.item;

import com.core.item.web.ItemReq;
import com.core.itemLevel.ItemLevelRepo;
import com.core.itemType.ItemTypeRepo;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepo itemRepo;
    private final ItemTypeRepo itemTypeRepo;
    private final ItemLevelRepo itemLevelRepo;
    private final MessageSource messageSource;

    public ItemService(ItemRepo itemRepo,
                            MessageSource messageSource,
                       ItemLevelRepo itemLevelRepo,
                       ItemTypeRepo itemTypeRepo){
        this.itemRepo = itemRepo;
        this.messageSource = messageSource;
        this.itemLevelRepo = itemLevelRepo;
        this.itemTypeRepo = itemTypeRepo;
    }

    public Item getItemById(Integer id){
        return itemRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.ItemWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public List<Item> getItemByCharacterId(Integer id, Pageable pageable){
        return itemRepo.findByCharacterId(id);
    }

    public Page<Item> findAllItem(Pageable pageable){
        return itemRepo.findAll(pageable);
    }

    public Item createItem(ItemReq req) {
        Item item = new Item();
        this.prepareItem(item,req);
        itemRepo.save(item);
        return item;
    }

    @Transactional
    public void deleteItem(Integer id) {
        try {
            itemRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.ItemWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public Item updateItem(Item item, ItemReq req){
        this.prepareItem(item,req);
        itemRepo.save(item);
        return item;
    }
    public void prepareItem(Item item, ItemReq itemBaseReq){
        item.setName(itemBaseReq.getName());
        item.setDamage(itemBaseReq.getDamage());
        item.setArmor(itemBaseReq.getArmor());
        item.setCharacterId(itemBaseReq.getCharacterId());
        item.setItemLevel(itemLevelRepo.getOne(itemBaseReq.getItemLevel()));
        item.setItemType(itemTypeRepo.getOne(itemBaseReq.getItemType()));
    }
}
