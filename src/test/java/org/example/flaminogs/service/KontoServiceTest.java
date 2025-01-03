package org.example.flaminogs.service;

import org.example.flaminogs.entity.Konto;
import org.example.flaminogs.entity.UserToken;
import org.example.flaminogs.repository.KontoRepository;
import org.example.flaminogs.repository.UserTokenRepository;
import org.example.flaminogs.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KontoServiceTest {
    private KontoService kontoService;

    @Mock
    private KontoRepository kontoRepository;

    @Mock
    private UserTokenRepository userTokenRepository;

    @Mock
    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() {
        kontoService = new KontoService(kontoRepository,userTokenRepository,jwtUtils);
    }

    @Test
    void testSave() {
        Konto konto = new Konto();
        konto.setLogin("user");
        konto.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));

        when(kontoRepository.save(konto)).thenReturn(konto);

        Konto savedKonto = kontoService.save(konto);

        assertNotNull(savedKonto);
        assertEquals("user", savedKonto.getLogin());
        verify(kontoRepository, times(1)).save(konto);
    }

    @Test
    void testFindAll() {
        List<Konto> konta = List.of(new Konto(), new Konto());

        when(kontoRepository.findAll()).thenReturn(konta);

        List<Konto> result = kontoService.findAll();

        assertEquals(2, result.size());
        verify(kontoRepository, times(1)).findAll();
    }

    @Test
    void testAuthenticateSuccess() {
        String login = "user";
        String password = "password";
        Konto konto = new Konto();
        konto.setLogin(login);
        konto.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

        when(kontoRepository.findAll()).thenReturn(List.of(konto));
        when(jwtUtils.generateToken(login)).thenReturn("token123");
        when(jwtUtils.getExpiration("token123")).thenReturn(new Date());

        String token = kontoService.authenticate(login, password);

        assertNotNull(token);
        assertEquals("token123", token);
        verify(kontoRepository, times(1)).findAll();
        verify(userTokenRepository, times(1)).save(any(UserToken.class));
    }
}
