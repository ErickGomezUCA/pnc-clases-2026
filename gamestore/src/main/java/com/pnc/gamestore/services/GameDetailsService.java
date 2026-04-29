package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.GameDetails;
import com.pnc.gamestore.model.Reviews;
import com.pnc.gamestore.repositories.GameDetailsRepository;
import com.pnc.gamestore.repositories.GameRepository;
import com.pnc.gamestore.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameDetailsService {

    @Autowired private GameDetailsRepository gameDetailsRepository;
    @Autowired private GameRepository gameRepository;

    public List<GameDetails> getAll() { return gameDetailsRepository.findAll(); }

    public ResponseEntity<GameDetails> create(GameDetails details) {
        // La excepción: un juego PUEDE no tener detalles, pero si se crean,
        // publishYear y game no pueden ser nulos
        if (details.getGame() == null || details.getPublishYear() == null)
            return ResponseEntity.badRequest().build();

        int year = details.getPublishYear();
        if (year < 1975 || year > java.time.Year.now().getValue())
            return ResponseEntity.badRequest().build();

        Game game = gameRepository.findById(details.getGame().getId()).orElse(null);
        if (game == null) return ResponseEntity.badRequest().build();

        details.setGame(game);
        return ResponseEntity.ok(gameDetailsRepository.save(details));
    }

    public ResponseEntity<GameDetails> update(UUID id, GameDetails updated) {
        GameDetails existing = gameDetailsRepository.findById(id).orElse(null);
        if (existing == null) return ResponseEntity.badRequest().build();

        if (updated.getPublishYear() != null) {
            int year = updated.getPublishYear();
            if (year < 1975 || year > java.time.Year.now().getValue())
                return ResponseEntity.badRequest().build();
            existing.setPublishYear(year);
        }
        if (updated.getAbout() != null) existing.setAbout(updated.getAbout());

        return ResponseEntity.ok(gameDetailsRepository.save(existing));
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (!gameDetailsRepository.existsById(id)) return ResponseEntity.badRequest().build();
        gameDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
