package org.example.flaminogs.service;

import org.example.flaminogs.entity.Wiadomosc;
import org.example.flaminogs.repository.WiadomoscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WiadomoscService {
    private final WiadomoscRepository wiadomoscRepository;

    @Autowired
    public WiadomoscService(WiadomoscRepository wiadomoscRepository) {
        this.wiadomoscRepository = wiadomoscRepository;
    }

    public Wiadomosc save(Wiadomosc wiadomosc){
        return wiadomoscRepository.save(wiadomosc);
    }

    public List<Wiadomosc> getConversation(String user1, String user2) {
        return wiadomoscRepository.findConversationBetween(user1, user2);
    }
}
