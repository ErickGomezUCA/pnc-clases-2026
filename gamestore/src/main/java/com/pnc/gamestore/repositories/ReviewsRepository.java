package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewsRepository extends JpaRepository<Reviews, UUID> {
    List<Reviews> findByGameId(UUID gameId);
    boolean existsByUserAndGame(String user, Game game);
}

