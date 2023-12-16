package com.core.enemy.web;

import com.util.CallBackService;
import com.core.enemy.Enemy;
import com.core.enemy.EnemyConverter;
import com.core.enemy.EnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/enemy")
public class EnemyController {
    private final EnemyService service;
    private final EnemyConverter converter;
    private final MessageSource messageSource;
    @Autowired
    private CallBackService callbackService;

    public EnemyController(EnemyService service,
                           MessageSource messageSource,
                           EnemyConverter converter) {
        this.service = service;
        this.messageSource = messageSource;
        this.converter = converter;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public EnemyView getEnemyById(@PathVariable Integer id) {
        Enemy enemy = service.getEnemyById(id);
        checkNull(enemy, id);
        return converter.convert(enemy);
    }

    @GetMapping
    @ResponseBody
    public Page<EnemyView> getAllEnemy(@PageableDefault(sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllEnemy(pageable)
                .map(converter::convert);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EnemyView createEnemy(@RequestBody @Valid EnemyReq req) {
        return converter.convert(service.createEnemy(req));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnemy(@PathVariable Integer id) {
        Enemy Enemy = service.getEnemyById(id);
        checkNull(Enemy, id);
        service.deleteEnemy(id);
    }

    @PutMapping("/{id}")
    public EnemyView updateEnemy(@PathVariable(name = "id") Integer id,
                                 @RequestBody @Valid EnemyReq req) {
        Enemy Enemy = service.getEnemyById(id);
        checkNull(Enemy, id);
        return converter.convert(service.updateEnemy(Enemy, req));
    }

    private void checkNull(Enemy Enemy, Integer id) {
        if (Enemy == null) {
            String message = messageSource.getMessage(
                    "com.core.web.EnemyWithIdNotExists", new Object[]{id},
                    LocaleContextHolder.getLocale());
        }
    }

    @Async
    @GetMapping("/getEnemies/{locationId}")
    public CompletableFuture<List<EnemyView>> getEnemiesByLocationId(
            @PathVariable Integer locationId,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        CompletableFuture<List<EnemyView>> future = CompletableFuture.supplyAsync(() -> {
            CompletableFuture.runAsync(() -> callbackService.onSecondMicroserviceProcessed("Request accepted"));

            List<Enemy> enemies = service.getEnemyByLocationId(locationId, pageable);

            List<EnemyView> enemiesViews = enemies.stream()
                    .map(converter::convert)
                    .collect(Collectors.toList());

            return enemiesViews;
        });

        return future;
    }

}