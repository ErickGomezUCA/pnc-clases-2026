package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlatformsService {

    @Autowired
    private PlatformsRepository platformsRepository;

    public List<Platforms> getAll() { return platformsRepository.findAll(); }

    public Platforms getById(UUID id) { return platformsRepository.findById(id).orElse(null); }

    public ResponseEntity<Platforms> create(Platforms platform) {
        if (platform.getName() == null || platform.getCompany() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(platformsRepository.save(platform));
    }

    public ResponseEntity<Platforms> update(UUID id, Platforms updated) {
        Platforms existing = platformsRepository.findById(id).orElse(null);
        if (existing == null) return ResponseEntity.badRequest().build();
        if (updated.getName() != null) existing.setName(updated.getName());
        if (updated.getCompany() != null) existing.setCompany(updated.getCompany());
        return ResponseEntity.ok(platformsRepository.save(existing));
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (!platformsRepository.existsById(id)) return ResponseEntity.badRequest().build();
        platformsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
