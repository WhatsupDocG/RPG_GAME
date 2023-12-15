package com.core.characterLevel.web;

import com.core.itemLevel.ItemLevel;
import com.core.itemLevel.ItemLevelConverter;
import com.core.itemLevel.ItemLevelService;
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
@RequestMapping(value = "/itemLevel")
public class ItemLevelController {
        private final ItemLevelService service;
        private final ItemLevelConverter converter;
        private final MessageSource messageSource;

        public ItemLevelController(ItemLevelService service,
                                  MessageSource messageSource,
                                  ItemLevelConverter converter) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public ItemLevelView getItemLevelById(@PathVariable Integer id) {
            ItemLevel itemLevel = service.getItemLevelById(id);
            checkNull(itemLevel, id);
            return converter.convert(itemLevel);
        }

        @GetMapping
        @ResponseBody
        public Page <ItemLevelView> getAllItemLevel(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllItemLevel(pageable)
                    .map(converter::convert);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public ItemLevelView createItemLevel(@RequestBody @Valid ItemLevelReq req) {
            return converter.convert(service.createItemLevel(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteItemLevel(@PathVariable Integer id){
            ItemLevel itemLevel = service.getItemLevelById(id);
            checkNull(itemLevel, id);
            service.deleteItemLevel(id);
        }

        @PutMapping("/{id}")
        public ItemLevelView updateItemLevel(@PathVariable(name = "id") Integer id,
                                           @RequestBody @Valid ItemLevelReq req){
            ItemLevel itemLevel = service.getItemLevelById(id);
            checkNull(itemLevel, id);
            return converter.convert(service.updateItemLevel(itemLevel, req));
        }

        private void checkNull (ItemLevel itemLevel, Integer id) {
            if (itemLevel == null) {
                String message = messageSource.getMessage(
                        "com.core.web.ItemLevelWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

    }
