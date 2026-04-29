package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    Optional<Game> findById(UUID id);
    boolean existsByName(String name);
    boolean existsByNameAndPlatformsContaining(String name, Platforms platform);
}
