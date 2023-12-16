package com.core.EnemyLevel;

import com.core.EnemyLevel.web.EnemyLevelReq;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class EnemyLevelService {

    private final EnemyLevelRepo enemyLevelRepo;
    private final MessageSource messageSource;

    public EnemyLevelService(EnemyLevelRepo enemyLevelRepo,
                           MessageSource messageSource){
        this.enemyLevelRepo = enemyLevelRepo;
        this.messageSource = messageSource;
    }

    public EnemyLevel getEnemyLevelById(Integer id){
        return enemyLevelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.EnemyLevelWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<EnemyLevel> findAllEnemyLevel(Pageable pageable){
        return enemyLevelRepo.findAll(pageable);
    }

    public EnemyLevel createEnemyLevel(EnemyLevelReq req) {
        EnemyLevel enemyLevel = new EnemyLevel();
        this.prepareEnemyLevel(enemyLevel,req);
        enemyLevelRepo.save(enemyLevel);
        return enemyLevel;
    }

    @Transactional
    public void deleteEnemyLevel(Integer id) {
        try {
            enemyLevelRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.EnemyLevelWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public EnemyLevel updateEnemyLevel(EnemyLevel enemyLevel, EnemyLevelReq req){
        this.prepareEnemyLevel(enemyLevel,req);
        enemyLevelRepo.save(enemyLevel);
        return enemyLevel;
    }
    public void prepareEnemyLevel(EnemyLevel enemyLevel, EnemyLevelReq enemyLevelBaseReq){
        enemyLevel.setName(enemyLevelBaseReq.getName());
    }


}
