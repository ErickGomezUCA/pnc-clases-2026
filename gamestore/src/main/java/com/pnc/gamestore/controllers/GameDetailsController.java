package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.GameDetails;
import com.pnc.gamestore.services.GameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("details")
public class GameDetailsController {

    @Autowired
    private GameDetailsService gameDetailsService;

    @GetMapping
    public ResponseEntity<List<GameDetails>> getAll() {
        return ResponseEntity.ok(gameDetailsService.getAll());
    }

    @PostMapping
    public ResponseEntity<GameDetails> create(@RequestBody GameDetails details) {
        return gameDetailsService.create(details);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDetails> update(@PathVariable UUID id, @RequestBody GameDetails details) {
        return gameDetailsService.update(id, details);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return gameDetailsService.delete(id);
    }
}