package org.example.flaminogs.service;

import org.example.flaminogs.entity.Multimedium;
import org.example.flaminogs.repository.MultimediumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediumService {
    private final MultimediumRepository multimediumRepository;

    @Autowired
    public MultimediumService(MultimediumRepository multimediumRepository) {
        this.multimediumRepository = multimediumRepository;
    }

    public List<Multimedium> findAll() {
        return multimediumRepository.findAll();
    }

    public Multimedium save(Multimedium multimedium) {
        return multimediumRepository.save(multimedium);
    }
}
