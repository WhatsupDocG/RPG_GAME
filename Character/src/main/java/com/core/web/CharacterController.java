package com.core.web;

import com.core.Character;
import com.core.CharacterConverter;
import com.core.CharacterService;
import com.util.CallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/character")
@CrossOrigin(origins = "http://localhost:8383")
public class CharacterController {
        private final CharacterService service;
        private final CharacterConverter converter;
        private final MessageSource messageSource;
        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private CallBackService callbackService;

        @Autowired
        public CharacterController(CharacterService service,
                                  MessageSource messageSource,
                                   CharacterConverter converter,
                                   RestTemplate restTemplate,
                                   CallBackService callbackService) {
            this.service = service;
            this.messageSource = messageSource;
            this.converter = converter;
            this.restTemplate = restTemplate;
            this.callbackService = callbackService;
        }

        @GetMapping("/{id}")
        @ResponseBody
        public CharacterView getCharacterById(@PathVariable Integer id) {
            Character character = service.getCharacterById(id);
            checkNull(character, id);
            return converter.convert(character);
        }

    @GetMapping("/getItems")
    public CompletableFuture<ResponseEntity<Object>> requestItemsFromSecondMicroservice() {
        String secondMicroserviceUrl = "http://localhost:9102/item/item/getItems";

        CompletableFuture<ResponseEntity<List<Object>>> futureResponse = CompletableFuture.supplyAsync(() -> {
            try {
                return restTemplate.exchange(
                        secondMicroserviceUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Object>>() {}
                );
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).<List<Object>>body(null);
            }
        });

        callbackService.onSecondMicroserviceProcessed("Request sent");

        CompletableFuture<ResponseEntity<Object>> futureItems = futureResponse.thenApply(responseEntity -> {
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                Object result = responseEntity.getBody();
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(responseEntity.getStatusCode()).<Object>body(null);
            }
        });

        return futureItems;
    }




    @GetMapping
        @ResponseBody
        public Page <CharacterView> getAllCharacter(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllCharacter(pageable)
                    .map(converter::convert);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public CharacterView createCharacter(@RequestBody @Valid CharacterReq req) {
            return converter.convert(service.createCharacter(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteCharacter(@PathVariable Integer id){
            Character character = service.getCharacterById(id);
            checkNull(character, id);
            service.deleteCharacter(id);
        }

        @PutMapping("/{id}")
        public CharacterView updateCharacter(@PathVariable(name = "id") Integer id,
                                           @RequestBody @Valid CharacterReq req){
            Character character = service.getCharacterById(id);
            checkNull(character, id);
            return converter.convert(service.updateCharacter(character, req));
        }

        private void checkNull (Character character, Integer id) {
            if (character == null) {
                String message = messageSource.getMessage(
                        "com.core.web.CharacterWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

}
