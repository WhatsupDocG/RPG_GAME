package com.core.EnemyLevel;

import com.core.EnemyLevel.web.EnemyLevelView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class EnemyLevelConverter implements Converter<EnemyLevel, EnemyLevelView> {
    @Override
    public EnemyLevelView convert(@NonNull EnemyLevel enemyLevel) {
        EnemyLevelView view = new EnemyLevelView();
        view.setId(enemyLevel.getId());
        view.setName(enemyLevel.getName());
        return view;
    }

}
