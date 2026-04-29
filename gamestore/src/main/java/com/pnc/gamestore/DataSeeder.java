package com.pnc.gamestore;

import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataSeeder implements ApplicationRunner {

    private final PlatformsRepository platformsRepository;

    public DataSeeder(PlatformsRepository platformsRepository) {
        this.platformsRepository = platformsRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (platformsRepository.count() == 0) {
            platformsRepository.saveAll(List.of(
                    new Platforms("Nintendo Switch", "Nintendo"),
                    new Platforms("PlayStation 5", "Sony"),
                    new Platforms("Xbox Series X", "Microsoft"),
                    new Platforms("PC", "Various"),
                    new Platforms("Game Boy Advance", "Nintendo")
            ));
        }
    }
}