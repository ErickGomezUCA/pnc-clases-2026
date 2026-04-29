package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.Reviews;
import com.pnc.gamestore.repositories.GameRepository;
import com.pnc.gamestore.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired private GameRepository gameRepository;

    public List<Reviews> getAll() { return reviewsRepository.findAll(); }

    public List<Reviews> getByGame(UUID gameId) { return reviewsRepository.findByGameId(gameId); }

    public ResponseEntity<Reviews> create(Reviews review) {
        // Nulos
        if (review.getUser() == null || review.getRating() == null || review.getGame() == null)
            return ResponseEntity.badRequest().build();

        Game game = gameRepository.findById(review.getGame().getId()).orElse(null);
        if (game == null) return ResponseEntity.badRequest().build();

        // Un solo review por usuario por juego
        if (reviewsRepository.existsByUserAndGame(review.getUser(), game))
            return ResponseEntity.badRequest().build();

        review.setGame(game);
        return ResponseEntity.ok(reviewsRepository.save(review));
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (!reviewsRepository.existsById(id)) return ResponseEntity.badRequest().build();
        reviewsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
