package com.core.location.web;

import com.core.location.Location;
import com.core.location.LocationConverter;
import com.core.location.LocationService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/location")
@CrossOrigin(origins = "http://localhost:8383")
public class LocationController {
        private final LocationService service;
        private final LocationConverter converter;
        private final MessageSource messageSource;
        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private CallBackService callbackService;

        @Autowired
        public LocationController(LocationService service,
                                  MessageSource messageSource,
                                  LocationConverter converter,
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
        public LocationView getLocationById(@PathVariable Integer id) {
            Location location = service.getLocationById(id);
            checkNull(location, id);
            return converter.convert(location);
        }

    @GetMapping("/getEnemies/{locationId}")
    public CompletableFuture<ResponseEntity<Object>> getItems(
        @PathVariable Long locationId,
        @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        String secondMicroserviceUrl = "http://localhost:9105/enemy/enemy/getEnemies/"+ locationId;

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
        public Page <LocationView> getAllLocation(@PageableDefault(sort = "id",
                direction = Sort.Direction.ASC) Pageable pageable) {
            return service.findAllLocation(pageable)
                    .map(converter::convert);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public LocationView createLocation(@RequestBody @Valid LocationReq req) {
            return converter.convert(service.createLocation(req));
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteLocation(@PathVariable Integer id){
            Location location = service.getLocationById(id);
            checkNull(location, id);
            service.deleteLocation(id);
        }

        @PutMapping("/{id}")
        public LocationView updateLocation(@PathVariable(name = "id") Integer id,
                                            @RequestBody @Valid LocationReq req){
            Location location = service.getLocationById(id);
            checkNull(location, id);
            return converter.convert(service.updateLocation(location, req));
        }

        private void checkNull (Location location, Integer id) {
            if (location == null) {
                String message = messageSource.getMessage(
                        "com.core.location.web.LocationWithIdNotExists", new Object[]{id},
                        LocaleContextHolder.getLocale());
            }
        }

}
