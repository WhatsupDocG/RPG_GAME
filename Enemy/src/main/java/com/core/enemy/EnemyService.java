package com.core.enemy;

import com.core.enemy.web.EnemyReq;
import com.core.EnemyLevel.EnemyLevelRepo;
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
public class EnemyService {

    private final EnemyRepo enemyRepo;
    private final EnemyLevelRepo enemyLevelRepo;
    private final MessageSource messageSource;

    public EnemyService(EnemyRepo enemyRepo,
                            EnemyLevelRepo enemyLevelRepo,
                            MessageSource messageSource){
        this.enemyRepo = enemyRepo;
        this.enemyLevelRepo = enemyLevelRepo;
        this.messageSource = messageSource;
    }

    public Enemy getEnemyById(Integer id){
        return enemyRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.EnemyWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public List<Enemy> getEnemyByLocationId(Integer id, Pageable pageable){
        return enemyRepo.findByLocationId(id);
    }

    public Page<Enemy> findAllEnemy(Pageable pageable){
        return enemyRepo.findAll(pageable);
    }

    public Enemy createEnemy(EnemyReq req) {
        Enemy Enemy = new Enemy();
        this.prepareEnemy(Enemy,req);
        enemyRepo.save(Enemy);
        return Enemy;
    }

    @Transactional
    public void deleteEnemy(Integer id) {
        try {
            enemyRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.EnemyWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public Enemy updateEnemy(Enemy enemy, EnemyReq req){
        this.prepareEnemy(enemy,req);
        enemyRepo.save(enemy);
        return enemy;
    }
    public void prepareEnemy(Enemy enemy, EnemyReq enemyBaseReq){
        enemy.setName(enemyBaseReq.getName());
        enemy.setDamage(enemyBaseReq.getDamage());
        enemy.setHealth(enemyBaseReq.getHealth());
        enemy.setEnemyLevel(enemyLevelRepo.getOne(enemyBaseReq.getEnemyLevel()));
        enemy.setEnemyType(enemyBaseReq.getEnemyType());
        enemy.setLocationId(enemyBaseReq.getLocationId());
    }


}
