package org.example.flaminogs.service;

import org.example.flaminogs.entity.Konto;
import org.example.flaminogs.repository.KontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KontoService {

    private final KontoRepository kontoRepository;

    @Autowired
    public KontoService(KontoRepository kontoRepository) {
        this.kontoRepository = kontoRepository;
    }

    public Konto save(Konto konto) {
        return kontoRepository.save(konto);
    }

    public List<Konto> findAll() {
        return kontoRepository.findAll();
    }
    public Konto findById(String id) {
        return kontoRepository.getReferenceById(id);
    }
    public Konto authenticate(String login, String password) {
        List<Konto> konta = findAll();
        for (Konto konto : konta) {
            if (konto.getLogin().equals(login) && konto.getPassword().equals(password)) {
                return konto;
            }
        }
        return null;
    }


}
