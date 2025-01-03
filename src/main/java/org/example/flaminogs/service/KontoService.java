package org.example.flaminogs.service;

import org.example.flaminogs.entity.Konto;
import org.example.flaminogs.entity.UserToken;
import org.example.flaminogs.repository.KontoRepository;
import org.example.flaminogs.repository.UserTokenRepository;
import org.example.flaminogs.security.JwtUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KontoService {

    private final KontoRepository kontoRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public KontoService(KontoRepository kontoRepository, UserTokenRepository userTokenRepository, JwtUtils jwtUtils) {
        this.kontoRepository = kontoRepository;
        this.userTokenRepository = userTokenRepository;
        this.jwtUtils = jwtUtils;
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

    public String authenticate(String login, String password) {
        List<Konto> konta = findAll();
        for (Konto konto : konta) {
            if (konto.getLogin().equals(login) && BCrypt.checkpw(password, konto.getPassword())) {
                String token = jwtUtils.generateToken(login);

                UserToken userToken = new UserToken();
                userToken.setKonto(konto);
                userToken.setToken(token);
                userToken.setExpirationDate(jwtUtils.getExpiration(token));
                userTokenRepository.save(userToken);

                return token;
            }
        }
        return null;
    }
}
