package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Reviews;
import com.pnc.gamestore.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("review")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    @GetMapping
    public ResponseEntity<List<Reviews>> getAll() {
        return ResponseEntity.ok(reviewsService.getAll());
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Reviews>> getByGame(@PathVariable UUID gameId) {
        return ResponseEntity.ok(reviewsService.getByGame(gameId));
    }

    @PostMapping
    public ResponseEntity<Reviews> create(@RequestBody Reviews review) {
        return reviewsService.create(review);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return reviewsService.delete(id);
    }
}