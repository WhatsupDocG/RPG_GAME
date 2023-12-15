package com.core.spellLevel.web;

import com.core.spellLevel.SpellLevel;
import com.core.spellLevel.SpellLevelConverter;
import com.core.spellLevel.SpellLevelService;
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
@RequestMapping(value = "/spellLevel")
public class SpellLevelController {
        private final SpellLevelService service;
        private final SpellLevelConverter converter;
        private final MessageSource messageSource;

        public SpellLevelController(SpellLevelService service,
                                  MessageSource messageSource,
                                  SpellLevelConverter converter) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public SpellLevelView getSpellLevelById(@PathVariable Integer id) {
            SpellLevel spellLevel = service.getSpellLevelById(id);
            checkNull(spellLevel, id);
            return converter.convert(spellLevel);
        }

        @GetMapping
        @ResponseBody
        public Page <SpellLevelView> getAllSpellLevel(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllSpellLevel(pageable)
                    .map(converter::convert);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public SpellLevelView createSpellLevel(@RequestBody @Valid SpellLevelReq req) {
            return converter.convert(service.createSpellLevel(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteSpellLevel(@PathVariable Integer id){
            SpellLevel spellLevel = service.getSpellLevelById(id);
            checkNull(spellLevel, id);
            service.deleteSpellLevel(id);
        }

        @PutMapping("/{id}")
        public SpellLevelView updateSpellLevel(@PathVariable(name = "id") Integer id,
                                           @RequestBody @Valid SpellLevelReq req){
            SpellLevel spellLevel = service.getSpellLevelById(id);
            checkNull(spellLevel, id);
            return converter.convert(service.updateSpellLevel(spellLevel, req));
        }

        private void checkNull (SpellLevel spellLevel, Integer id) {
            if (spellLevel == null) {
                String message = messageSource.getMessage(
                        "com.core.web.SpellLevelWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

    }
