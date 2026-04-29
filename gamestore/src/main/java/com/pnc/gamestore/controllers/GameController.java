package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("game")
public class GameController {
    @Autowired private GameService gameService;

    @GetMapping         public ResponseEntity<List<Game>> getAll() { return ResponseEntity.ok(gameService.getAll()); }
    @GetMapping("/{id}") public ResponseEntity<Game> getById(@PathVariable UUID id) { return ResponseEntity.ok(gameService.getById(id)); }
    @PostMapping        public ResponseEntity<Game> create(@RequestBody Game game) { return gameService.create(game); }
    @PutMapping("/{id}") public ResponseEntity<Game> update(@PathVariable UUID id, @RequestBody Game game) { return gameService.update(id, game); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable UUID id) { return gameService.delete(id); }
}