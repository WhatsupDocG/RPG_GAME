package com.core.itemType;

import com.core.itemType.web.ItemTypeView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ItemTypeConverter implements Converter<ItemType, ItemTypeView> {
    @Override
    public ItemTypeView convert(@NonNull ItemType itemType) {
        ItemTypeView view = new ItemTypeView();
        view.setId(itemType.getId());
        view.setName(itemType.getName());
        return view;
    }

}
