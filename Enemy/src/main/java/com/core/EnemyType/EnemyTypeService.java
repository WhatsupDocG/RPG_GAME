package com.core.EnemyType;

import com.core.EnemyType.web.EnemyTypeReq;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class EnemyTypeService {

    private final EnemyTypeRepo enemyTypeRepo;
    private final MessageSource messageSource;

    public EnemyTypeService(EnemyTypeRepo enemyTypeRepo,
                            MessageSource messageSource){
        this.enemyTypeRepo = enemyTypeRepo;
        this.messageSource = messageSource;
    }

    public EnemyType getEnemyTypeById(Integer id){
        return enemyTypeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.web.EnemyTypeWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<EnemyType> findAllEnemyType(Pageable pageable){
        return enemyTypeRepo.findAll(pageable);
    }

    public EnemyType createEnemyType(EnemyTypeReq req) {
        EnemyType enemyType = new EnemyType();
        this.prepareEnemyType(enemyType,req);
        enemyTypeRepo.save(enemyType);
        return enemyType;
    }

    @Transactional
    public void deleteEnemyType(Integer id) {
        try {
            enemyTypeRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.web.EnemyTypeWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public EnemyType updateEnemyType(EnemyType enemyType, EnemyTypeReq req){
        this.prepareEnemyType(enemyType,req);
        enemyTypeRepo.save(enemyType);
        return enemyType;
    }
    public void prepareEnemyType(EnemyType enemyType, EnemyTypeReq enemyTypeBaseReq){
        enemyType.setName(enemyTypeBaseReq.getName());
    }


}
