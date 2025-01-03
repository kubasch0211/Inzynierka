package org.example.flaminogs.service;

import org.example.flaminogs.entity.Komentarz;
import org.example.flaminogs.entity.Multimedium;
import org.example.flaminogs.klasy.KomentarzRsp;
import org.example.flaminogs.repository.KomentarzRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KomentarzServiceTest {

    @Mock
    private KomentarzRepository komentarzRepository;

    @Mock
    private MultimediumService multimediumService;

    private KomentarzService komentarzService;

    @BeforeEach
    public void setUp(){
        komentarzService = new KomentarzService(komentarzRepository,multimediumService);
    }

    @Test
    public void testFindAll() {
        final List<Komentarz> komentarze = List.of(new Komentarz(), new Komentarz());

        when(komentarzRepository.findAll()).thenReturn(komentarze);

        final List<Komentarz> result = komentarzService.findAll();

        assertEquals(2, result.size());
        verify(komentarzRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        Komentarz komentarz = new Komentarz();
        when(komentarzRepository.save(komentarz)).thenReturn(komentarz);

        Komentarz result = komentarzService.save(komentarz);

        assertNotNull(result);
        verify(komentarzRepository, times(1)).save(komentarz);
    }

    @Test
    public void testDelete() {
        Integer komentarzId = 1;

        komentarzService.delete(komentarzId);

        verify(komentarzRepository, times(1)).deleteById(komentarzId);
    }

    @Test
    public void testGetAllKomentarze() {
        Komentarz komentarz1 = new Komentarz();
        komentarz1.setId(1);
        Komentarz komentarz2 = new Komentarz();
        komentarz2.setId(2);

        Multimedium multimedium1 = new Multimedium();
        multimedium1.setIdkomentarza(1);
        multimedium1.setMultimedium("multimedia1");

        Multimedium multimedium2 = new Multimedium();
        multimedium2.setIdkomentarza(1);
        multimedium2.setMultimedium("multimedia2");

        List<Komentarz> komentarze = List.of(komentarz1, komentarz2);
        List<Multimedium> multimedia =  List.of(multimedium1, multimedium2);

        when(komentarzRepository.findAll()).thenReturn(komentarze);
        when(multimediumService.findAll()).thenReturn(multimedia);

        List<KomentarzRsp> result = komentarzService.getAllKomentarze();

        assertEquals(2, result.size());
        assertTrue(result.get(0).getMultimedia().contains("multimedia1"));
        assertTrue(result.get(0).getMultimedia().contains("multimedia2"));
        assertTrue(result.get(1).getMultimedia().isEmpty());

        verify(komentarzRepository, times(1)).findAll();
    }
}
