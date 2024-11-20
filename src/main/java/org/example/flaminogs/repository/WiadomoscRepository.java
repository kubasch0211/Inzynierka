package org.example.flaminogs.repository;

import org.example.flaminogs.entity.Wiadomosc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WiadomoscRepository extends JpaRepository<Wiadomosc, Integer> {
    @Query("SELECT w FROM Wiadomosc w WHERE " +
            "(w.loginsender = :user1 AND w.loginreceiver = :user2) OR " +
            "(w.loginsender = :user2 AND w.loginreceiver = :user1) " +
            "ORDER BY w.date ASC")
    List<Wiadomosc> findConversationBetween(@Param("user1") String user1, @Param("user2") String user2);
}
