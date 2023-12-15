package com.core.spell.web;

import com.core.spell.Spell;
import com.core.spell.SpellConverter;
import com.core.spell.SpellService;
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
@RequestMapping(value = "/spell")
public class SpellController {
        private final SpellService service;
        private final SpellConverter converter;
        private final MessageSource messageSource;
        @Autowired
        private CallBackService callbackService;

        public SpellController(SpellService service,
                                  MessageSource messageSource,
                                   SpellConverter converter) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public SpellView getSpellById(@PathVariable Integer id) {
            Spell spell = service.getSpellById(id);
            checkNull(spell, id);
            return converter.convert(spell);
        }

        @GetMapping
        @ResponseBody
        public Page <SpellView> getAllSpell(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllSpell(pageable)
                    .map(converter::convert);
        }

    @Async
    @GetMapping("/getSpells")
    public CompletableFuture<List<SpellView>> getSpells(@PageableDefault(sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {

        CompletableFuture<List<SpellView>> future = CompletableFuture.supplyAsync(() -> {
            CompletableFuture.runAsync(() -> callbackService.onSecondMicroserviceProcessed("Request accepted"));

            List<SpellView> spells =  service.findAllSpell(pageable)
                    .getContent()
                    .stream()
                    .map(converter::convert)
                    .collect(Collectors.toList());
            return spells;
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


    @PostMapping("/receiveSpellsPage")
        public ResponseEntity<String> receiveSpellsPage(@RequestBody Page<SpellView> spellsPage) {
            return ResponseEntity.ok("OK");

        }

    @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public SpellView createSpell(@RequestBody @Valid SpellReq req) {
            return converter.convert(service.createSpell(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteSpell(@PathVariable Integer id){
            Spell spell = service.getSpellById(id);
            checkNull(spell, id);
            service.deleteSpell(id);
        }

        @PutMapping("/{id}")
        public SpellView updateSpell(@PathVariable(name = "id") Integer id,
                                           @RequestBody @Valid SpellReq req){
            Spell spell = service.getSpellById(id);
            checkNull(spell, id);
            return converter.convert(service.updateSpell(spell, req));
        }

        private void checkNull (Spell spell, Integer id) {
            if (spell == null) {
                String message = messageSource.getMessage(
                        "com.core.web.SpellWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

    }