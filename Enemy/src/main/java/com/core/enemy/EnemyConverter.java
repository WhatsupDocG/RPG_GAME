package com.core.enemy;

import com.core.enemy.web.EnemyView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class EnemyConverter implements Converter<Enemy, EnemyView> {
    @Override
    public EnemyView convert(@NonNull Enemy enemy) {
        EnemyView view = new EnemyView();
        view.setId(enemy.getId());
        view.setName(enemy.getName());
        view.setDamage(enemy.getDamage());
        view.setHealth(enemy.getHealth());
        view.setEnemyLevel(enemy.getEnemyLevel().getId());
        //view.setEnemyType(enemy.getEnemyType());
        //view.setLocationId(enemy.getLocationId());
        return view;
    }

}
