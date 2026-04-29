package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Classification;
import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.repositories.GameRepository;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired private GameRepository gameRepository;
    @Autowired private PlatformsRepository platformsRepository;

    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    public Game getById(UUID id) {
        return gameRepository.findById(id).orElse(null);
    }

    public ResponseEntity<Game> create(Game game) {
        // Nulos / vacíos
        if (game.getName() == null || game.getClassification() == null
                || game.getGenres() == null || game.getGenres().isEmpty()
                || game.getDev() == null
                || game.getPlatforms() == null || game.getPlatforms().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Nombre duplicado
        if (gameRepository.existsByName(game.getName())) {
            return ResponseEntity.badRequest().build();
        }

        for (Platforms p : game.getPlatforms()) {
            // Mismo juego en misma plataforma
            if (gameRepository.existsByNameAndPlatformsContaining(game.getName(), p)) {
                return ResponseEntity.badRequest().build();
            }
            // Juego M en plataforma Nintendo
            if (game.getClassification() == Classification.M
                    && p.getCompany() != null
                    && p.getCompany().equalsIgnoreCase("Nintendo")) {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok(gameRepository.save(game));
    }

    public ResponseEntity<Game> update(UUID id, Game updated) {
        Game existing = gameRepository.findById(id).orElse(null);
        if (existing == null) return ResponseEntity.badRequest().build();

        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getClassification() != null) existing.setClassification(updated.getClassification());
        if (updated.getGenres() != null && !updated.getGenres().isEmpty()) existing.setGenres(updated.getGenres());
        if (updated.getDev() != null) existing.setDev(updated.getDev());

        return ResponseEntity.ok(gameRepository.save(existing));
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (!gameRepository.existsById(id)) return ResponseEntity.badRequest().build();
        gameRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}