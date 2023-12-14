package com.core.itemType.web;

import com.core.itemType.ItemType;
import com.core.itemType.ItemTypeConverter;
import com.core.itemType.ItemTypeService;
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
@RequestMapping(value = "/itemType")
public class ItemTypeController {
        private final ItemTypeService service;
        private final ItemTypeConverter converter;
        private final MessageSource messageSource;

        public ItemTypeController(ItemTypeService service,
                                   MessageSource messageSource,
                                   ItemTypeConverter converter) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public ItemTypeView getItemTypeById(@PathVariable Integer id) {
            ItemType itemType = service.getItemTypeById(id);
            checkNull(itemType, id);
            return converter.convert(itemType);
        }

        @GetMapping
        @ResponseBody
        public Page <ItemTypeView> getAllItemType(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllItemType(pageable)
                    .map(converter::convert);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public ItemTypeView createItemType(@RequestBody @Valid ItemTypeReq req) {
            return converter.convert(service.createItemType(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteItemType(@PathVariable Integer id){
            ItemType itemType = service.getItemTypeById(id);
            checkNull(itemType, id);
            service.deleteItemType(id);
        }

        @PutMapping("/{id}")
        public ItemTypeView updateItemType(@PathVariable(name = "id") Integer id,
                                             @RequestBody @Valid ItemTypeReq req){
            ItemType itemType = service.getItemTypeById(id);
            checkNull(itemType, id);
            return converter.convert(service.updateItemType(itemType, req));
        }

        private void checkNull (ItemType itemType, Integer id) {
            if (itemType == null) {
                String message = messageSource.getMessage(
                        "com.core.web.ItemTypeWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

    }
