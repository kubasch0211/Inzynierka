package org.example.flaminogs.service;

import org.example.flaminogs.entity.LokalizacjaIStatus;
import org.example.flaminogs.repository.LokalizacjaIStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LokalizacjaIStatusService {
    private final LokalizacjaIStatusRepository lokalizacjaIStatusRepository;

    @Autowired
    public LokalizacjaIStatusService(LokalizacjaIStatusRepository lokalizacjaIStatusRepository) {
        this.lokalizacjaIStatusRepository = lokalizacjaIStatusRepository;
    }

    public List<LokalizacjaIStatus> findAll() {
        return lokalizacjaIStatusRepository.findAll();
    }

    public LokalizacjaIStatus save(LokalizacjaIStatus lokalizacjaIStatus) {
        return lokalizacjaIStatusRepository.save(lokalizacjaIStatus);
    }

    public LokalizacjaIStatus getById(String id) {
        return lokalizacjaIStatusRepository.findById(id).orElse(new LokalizacjaIStatus(id));
    }
}
