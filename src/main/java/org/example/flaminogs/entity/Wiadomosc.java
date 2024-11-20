package org.example.flaminogs.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wiadomosci")
public class Wiadomosc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String loginsender;

    @Column(nullable = false, length = 255)
    private String loginreceiver;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private LocalDateTime date;

    public Wiadomosc date(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public Wiadomosc text(String text) {
        this.text = text;
        return this;
    }

    public Wiadomosc loginreceiver(String loginreceiver) {
        this.loginreceiver = loginreceiver;
        return this;
    }

    public Wiadomosc loginsender(String loginsender) {
        this.loginsender = loginsender;
        return this;
    }

    public Wiadomosc id(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginsender() {
        return loginsender;
    }

    public void setLoginsender(String loginSender) {
        this.loginsender = loginSender;
    }

    public String getLoginreceiver() {
        return loginreceiver;
    }

    public void setLoginreceiver(String loginReceiver) {
        this.loginreceiver = loginReceiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
