package com.core.item.web;

import com.core.item.Item;
import com.core.item.ItemConverter;
import com.core.item.ItemService;
import com.util.CallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
        private final ItemService service;
        private final ItemConverter converter;
        private final MessageSource messageSource;
        @Autowired
        private CallBackService callbackService;

        public ItemController(ItemService service,
                                  MessageSource messageSource,
                                   ItemConverter converter) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public ItemView getItemById(@PathVariable Integer id) {
            Item item = service.getItemById(id);
            checkNull(item, id);
            return converter.convert(item);
        }

        @GetMapping
        @ResponseBody
        public Page <ItemView> getAllItem(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllItem(pageable)
                    .map(converter::convert);
        }

    @Async
    @GetMapping("/getItems/{characterId}")
    public CompletableFuture<List<ItemView>> getItemsByCharacterId(
            @PathVariable Integer characterId,
            @PageableDefault(sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {

        CompletableFuture<List<ItemView>> future = CompletableFuture.supplyAsync(() -> {
            CompletableFuture.runAsync(() -> callbackService.onSecondMicroserviceProcessed("Request accepted"));

            List<Item> items = service.getItemByCharacterId(characterId, pageable);

            List<ItemView> itemViews = items.stream()
                    .map(converter::convert)
                    .collect(Collectors.toList());

            return itemViews;
        });

        return future.thenComposeAsync(result ->
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return result;
                })
        );
    }





    @PostMapping("/receiveItemsPage")
        public ResponseEntity<String> receiveItemsPage(@RequestBody Page<ItemView> itemsPage) {
            return ResponseEntity.ok("OK");

        }

    @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public ItemView createItem(@RequestBody @Valid ItemReq req) {
            return converter.convert(service.createItem(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteItem(@PathVariable Integer id){
            Item item = service.getItemById(id);
            checkNull(item, id);
            service.deleteItem(id);
        }

        @PutMapping("/{id}")
        public ItemView updateItem(@PathVariable(name = "id") Integer id,
                                           @RequestBody @Valid ItemReq req){
            Item item = service.getItemById(id);
            checkNull(item, id);
            return converter.convert(service.updateItem(item, req));
        }

        private void checkNull (Item item, Integer id) {
            if (item == null) {
                String message = messageSource.getMessage(
                        "com.core.web.ItemWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

    }
