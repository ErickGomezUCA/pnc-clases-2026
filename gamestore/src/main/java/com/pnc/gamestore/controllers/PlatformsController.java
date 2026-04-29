package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.services.GameService;
import com.pnc.gamestore.services.PlatformsService;
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
@RequestMapping("platform")
public class PlatformsController {
    @Autowired private PlatformsService platformsService;

    @GetMapping          public ResponseEntity<List<Platforms>> getAll() { return ResponseEntity.ok(platformsService.getAll()); }
    @GetMapping("/{id}") public ResponseEntity<Platforms> getById(@PathVariable UUID id) { return ResponseEntity.ok(platformsService.getById(id)); }
    @PostMapping         public ResponseEntity<Platforms> create(@RequestBody Platforms p) { return platformsService.create(p); }
    @PutMapping("/{id}") public ResponseEntity<Platforms> update(@PathVariable UUID id, @RequestBody Platforms p) { return platformsService.update(id, p); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable UUID id) { return platformsService.delete(id); }
}
