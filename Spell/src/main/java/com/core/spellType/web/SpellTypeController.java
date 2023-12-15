package com.core.spellType.web;

import com.core.spellType.SpellType;
import com.core.spellType.SpellTypeConverter;
import com.core.spellType.SpellTypeService;
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
@RequestMapping(value = "/spellType")
public class SpellTypeController {
        private final SpellTypeService service;
        private final SpellTypeConverter converter;
        private final MessageSource messageSource;

        public SpellTypeController(SpellTypeService service,
                                   MessageSource messageSource,
                                   SpellTypeConverter converter) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public SpellTypeView getSpellTypeById(@PathVariable Integer id) {
            SpellType spellType = service.getSpellTypeById(id);
            checkNull(spellType, id);
            return converter.convert(spellType);
        }

        @GetMapping
        @ResponseBody
        public Page <SpellTypeView> getAllSpellType(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllSpellType(pageable)
                    .map(converter::convert);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public SpellTypeView createSpellType(@RequestBody @Valid SpellTypeReq req) {
            return converter.convert(service.createSpellType(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteSpellType(@PathVariable Integer id){
            SpellType spellType = service.getSpellTypeById(id);
            checkNull(spellType, id);
            service.deleteSpellType(id);
        }

        @PutMapping("/{id}")
        public SpellTypeView updateSpellType(@PathVariable(name = "id") Integer id,
                                             @RequestBody @Valid SpellTypeReq req){
            SpellType spellType = service.getSpellTypeById(id);
            checkNull(spellType, id);
            return converter.convert(service.updateSpellType(spellType, req));
        }

        private void checkNull (SpellType spellType, Integer id) {
            if (spellType == null) {
                String message = messageSource.getMessage(
                        "com.core.web.SpellTypeWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

    }
