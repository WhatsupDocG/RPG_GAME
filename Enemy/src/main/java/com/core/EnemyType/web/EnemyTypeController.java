package com.core.EnemyType.web;

import com.core.EnemyType.EnemyType;
import com.core.EnemyType.EnemyTypeConverter;
import com.core.EnemyType.EnemyTypeService;
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
@RequestMapping(value = "/enemyType")
public class EnemyTypeController {
        private final EnemyTypeService service;
        private final EnemyTypeConverter converter;
        private final MessageSource messageSource;

        public EnemyTypeController(EnemyTypeService service,
                                   MessageSource messageSource,
                                   EnemyTypeConverter converter) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public EnemyTypeView getEnemyTypeById(@PathVariable Integer id) {
            EnemyType enemyType = service.getEnemyTypeById(id);
            checkNull(enemyType, id);
            return converter.convert(enemyType);
        }

        @GetMapping
        @ResponseBody
        public Page <EnemyTypeView> getAllEnemyType(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllEnemyType(pageable)
                    .map(converter::convert);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public EnemyTypeView createEnemyType(@RequestBody @Valid EnemyTypeReq req) {
            return converter.convert(service.createEnemyType(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteEnemyType(@PathVariable Integer id){
            EnemyType enemyType = service.getEnemyTypeById(id);
            checkNull(enemyType, id);
            service.deleteEnemyType(id);
        }

        @PutMapping("/{id}")
        public EnemyTypeView updateEnemyType(@PathVariable(name = "id") Integer id,
                                             @RequestBody @Valid EnemyTypeReq req){
            EnemyType enemyType = service.getEnemyTypeById(id);
            checkNull(enemyType, id);
            return converter.convert(service.updateEnemyType(enemyType, req));
        }

        private void checkNull (EnemyType enemyType, Integer id) {
            if (enemyType == null) {
                String message = messageSource.getMessage(
                        "com.core.web.EnemyTypeWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

    }
