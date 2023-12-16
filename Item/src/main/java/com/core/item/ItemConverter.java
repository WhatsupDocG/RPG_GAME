package com.core.item;

import com.core.item.web.ItemView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter implements Converter<Item, ItemView> {
    @Override
    public ItemView convert(@NonNull Item item) {
        ItemView view = new ItemView();
        view.setId(item.getId());
        view.setName(item.getName());
        view.setDamage(item.getDamage());
        view.setArmor(item.getArmor());
        view.setItemLevel(item.getItemLevel().getId());
        //view.setCharacterId(item.getCharacterId());
        //view.setItemType(item.getItemType().getId());
        return view;
    }

}
