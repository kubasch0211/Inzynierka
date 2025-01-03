package org.example.flaminogs.service;

import org.example.flaminogs.entity.LokalizacjaIStatus;
import org.example.flaminogs.repository.LokalizacjaIStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LokalizacjaIStatusServiceTest {
    @Mock
    private LokalizacjaIStatusRepository lokalizacjaIStatusRepository;

    private LokalizacjaIStatusService lokalizacjaIStatusService;
    @BeforeEach
    public void setUp(){
        lokalizacjaIStatusService = new LokalizacjaIStatusService(lokalizacjaIStatusRepository);
    }

    @Test
    void testFindAll() {
        List<LokalizacjaIStatus> expectedList = List.of(
                new LokalizacjaIStatus("login"),
                new LokalizacjaIStatus("login2")
        );

        when(lokalizacjaIStatusRepository.findAll()).thenReturn(expectedList);

        List<LokalizacjaIStatus> result = lokalizacjaIStatusService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("login", result.get(0).getLogin());
        assertEquals("login2", result.get(1).getLogin());
        verify(lokalizacjaIStatusRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        LokalizacjaIStatus lokalizacjaIStatus = new LokalizacjaIStatus("login");

        when(lokalizacjaIStatusRepository.save(lokalizacjaIStatus)).thenReturn(lokalizacjaIStatus);

        LokalizacjaIStatus result = lokalizacjaIStatusService.save(lokalizacjaIStatus);

        assertNotNull(result);
        assertEquals("login", result.getLogin());
        verify(lokalizacjaIStatusRepository, times(1)).save(lokalizacjaIStatus);
    }

    @Test
    void testGetByIdFound() {
        String login = "login";
        LokalizacjaIStatus expectedLokalizacja = new LokalizacjaIStatus(login);

        when(lokalizacjaIStatusRepository.findById(login)).thenReturn(Optional.of(expectedLokalizacja));

        LokalizacjaIStatus result = lokalizacjaIStatusService.getById(login);

        assertNotNull(result);
        assertEquals(login, result.getLogin());
        verify(lokalizacjaIStatusRepository, times(1)).findById(login);
    }

    @Test
    void testGetByIdNotFound() {
        String login = "id1";

        when(lokalizacjaIStatusRepository.findById(login)).thenReturn(Optional.empty());

        LokalizacjaIStatus result = lokalizacjaIStatusService.getById(login);

        assertNotNull(result);
        assertEquals(login, result.getLogin());
        verify(lokalizacjaIStatusRepository, times(1)).findById(login);
    }
}
