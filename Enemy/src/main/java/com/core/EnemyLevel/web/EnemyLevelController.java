package com.core.EnemyLevel.web;

import com.core.EnemyLevel.EnemyLevel;
import com.core.EnemyLevel.EnemyLevelConverter;
import com.core.EnemyLevel.EnemyLevelService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/enemyLevel")
public class EnemyLevelController {
        private final EnemyLevelService service;
        private final EnemyLevelConverter converter;
        private final MessageSource messageSource;

        public EnemyLevelController(EnemyLevelService service,
                                  MessageSource messageSource,
                                  EnemyLevelConverter converter) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public EnemyLevelView getEnemyLevelById(@PathVariable Integer id) {
            EnemyLevel enemyLevel = service.getEnemyLevelById(id);
            checkNull(enemyLevel, id);
            return converter.convert(enemyLevel);
        }

        @GetMapping
        @ResponseBody
        public Page <EnemyLevelView> getAllEnemyLevel(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllEnemyLevel(pageable)
                    .map(converter::convert);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public EnemyLevelView createEnemyLevel(@RequestBody @Valid EnemyLevelReq req) {
            return converter.convert(service.createEnemyLevel(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteEnemyLevel(@PathVariable Integer id){
            EnemyLevel enemyLevel = service.getEnemyLevelById(id);
            checkNull(enemyLevel, id);
            service.deleteEnemyLevel(id);
        }

        @PutMapping("/{id}")
        public EnemyLevelView updateEnemyLevel(@PathVariable(name = "id") Integer id,
                                           @RequestBody @Valid EnemyLevelReq req){
            EnemyLevel enemyLevel = service.getEnemyLevelById(id);
            checkNull(enemyLevel, id);
            return converter.convert(service.updateEnemyLevel(enemyLevel, req));
        }

        private void checkNull (EnemyLevel enemyLevel, Integer id) {
            if (enemyLevel == null) {
                String message = messageSource.getMessage(
                        "com.core.web.EnemyLevelWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

    }
