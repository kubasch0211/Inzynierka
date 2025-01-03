package org.example.flaminogs.service;

import org.example.flaminogs.entity.Wiadomosc;
import org.example.flaminogs.repository.WiadomoscRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WiadomoscServiceTest {
    private WiadomoscService wiadomoscService;

    @Mock
    private WiadomoscRepository wiadomoscRepository;

    @BeforeEach
    void setUp() {
        wiadomoscService = new WiadomoscService(wiadomoscRepository);
    }

    @Test
    void testSave() {
        Wiadomosc wiadomosc = new Wiadomosc();
        wiadomosc.setLoginsender("user1");
        wiadomosc.setLoginreceiver("user2");
        wiadomosc.setText("Hello, this is a test message.");

        when(wiadomoscRepository.save(wiadomosc)).thenReturn(wiadomosc);

        Wiadomosc result = wiadomoscService.save(wiadomosc);

        assertNotNull(result);
        assertEquals("user1", result.getLoginsender());
        assertEquals("user2", result.getLoginreceiver());
        assertEquals("Hello, this is a test message.", result.getText());
        verify(wiadomoscRepository, times(1)).save(wiadomosc);
    }

    @Test
    void testGetConversation() {
        String user1 = "user1";
        String user2 = "user2";
        Wiadomosc wiadomosc1 = new Wiadomosc();
        wiadomosc1.setLoginsender(user1);
        wiadomosc1.setLoginreceiver(user2);
        wiadomosc1.setText("Message 1");

        Wiadomosc wiadomosc2 = new Wiadomosc();
        wiadomosc2.setLoginsender(user2);
        wiadomosc2.setLoginreceiver(user1);
        wiadomosc2.setText("Message 2");

        List<Wiadomosc> conversation = List.of(wiadomosc1, wiadomosc2);

        when(wiadomoscRepository.findConversationBetween(user1, user2)).thenReturn(conversation);

        List<Wiadomosc> result = wiadomoscService.getConversation(user1, user2);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Message 1", result.get(0).getText());
        assertEquals("Message 2", result.get(1).getText());
        verify(wiadomoscRepository, times(1)).findConversationBetween(user1, user2);
    }
}
