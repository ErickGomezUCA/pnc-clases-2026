package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.GameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface GameDetailsRepository extends JpaRepository<GameDetails, UUID> {
}
