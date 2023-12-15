package com.core.characterLevel;

import com.core.itemLevel.web.ItemLevelView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ItemLevelConverter implements Converter<ItemLevel, ItemLevelView> {
    @Override
    public ItemLevelView convert(@NonNull ItemLevel itemLevel) {
        ItemLevelView view = new ItemLevelView();
        view.setId(itemLevel.getId());
        view.setName(itemLevel.getName());
        return view;
    }

}
