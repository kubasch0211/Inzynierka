package org.example.flaminogs.service;

import org.example.flaminogs.repository.MultimediumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.example.flaminogs.entity.Multimedium;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MultimediumServiceTest {
    private MultimediumService multimediumService;

    @Mock
    private MultimediumRepository multimediumRepository;

    @BeforeEach
    void setUp() {
        multimediumService = new MultimediumService(multimediumRepository);
    }

    @Test
    void testFindAll() {
        Multimedium multimedium1 = new Multimedium();
        multimedium1.setId(1);
        multimedium1.setMultimedium("Multimedia1");

        Multimedium multimedium2 = new Multimedium();
        multimedium2.setId(2);
        multimedium2.setMultimedium("Multimedia2");

        List<Multimedium> multimediaList = List.of(multimedium1, multimedium2);

        when(multimediumRepository.findAll()).thenReturn(multimediaList);

        List<Multimedium> result = multimediumService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Multimedia1", result.get(0).getMultimedium());
        assertEquals("Multimedia2", result.get(1).getMultimedium());
        verify(multimediumRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        Multimedium multimedium = new Multimedium();
        multimedium.setId(1);
        multimedium.setMultimedium("New Multimedia");

        when(multimediumRepository.save(multimedium)).thenReturn(multimedium);

        Multimedium result = multimediumService.save(multimedium);

        assertNotNull(result);
        assertEquals("New Multimedia", result.getMultimedium());
        verify(multimediumRepository, times(1)).save(multimedium);
    }
}
