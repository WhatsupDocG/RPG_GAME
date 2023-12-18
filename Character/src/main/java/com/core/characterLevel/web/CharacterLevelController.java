package com.core.characterLevel.web;

import com.core.characterLevel.CharacterLevel;
import com.core.characterLevel.CharacterLevelConverter;
import com.core.characterLevel.CharacterLevelService;
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
@RequestMapping(value = "/characterLevel")
public class CharacterLevelController {
        private final CharacterLevelService service;
        private final CharacterLevelConverter converter;
        private final MessageSource messageSource;

        public CharacterLevelController(CharacterLevelService service,
                                        MessageSource messageSource,
                                        CharacterLevelConverter converter) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public CharacterLevelView getCharacterLevelById(@PathVariable Integer id) {
            CharacterLevel characterLevel = service.getCharacterLevelById(id);
            checkNull(characterLevel, id);
            return converter.convert(characterLevel);
        }

        @GetMapping
        @ResponseBody
        public Page <CharacterLevelView> getAllCharacterLevel(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllCharacterLevel(pageable)
                    .map(converter::convert);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public CharacterLevelView createCharacterLevel(@RequestBody @Valid CharacterLevelReq req) {
            return converter.convert(service.createCharacterLevel(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteCharacterLevel(@PathVariable Integer id) {
            CharacterLevel characterLevel = service.getCharacterLevelById(id);
            checkNull(characterLevel, id);
            service.deleteCharacterLevel(id);
        }

        @PutMapping("/{id}")
        public CharacterLevelView updateCharacterLevel(@PathVariable(name = "id") Integer id,
                                                       @RequestBody @Valid CharacterLevelReq req) {
            CharacterLevel characterLevel = service.getCharacterLevelById(id);
            checkNull(characterLevel, id);
            return converter.convert(service.updateCharacterLevel(characterLevel, req));
        }

        private void checkNull (CharacterLevel characterLevel, Integer id) {
            if (characterLevel == null) {
                String message = messageSource.getMessage(
                        "com.core.web.CharacterLevelWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

    }
