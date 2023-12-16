package com.core.EnemyType;

import com.core.EnemyType.web.EnemyTypeView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class EnemyTypeConverter implements Converter<EnemyType, EnemyTypeView> {
    @Override
    public EnemyTypeView convert(@NonNull EnemyType enemyType) {
        EnemyTypeView view = new EnemyTypeView();
        view.setId(enemyType.getId());
        view.setName(enemyType.getName());
        return view;
    }

}
