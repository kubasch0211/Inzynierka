package org.example.flaminogs.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "komentarze")
public class Komentarz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String login;

    private String text;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer idposta;

    public Komentarz(String login, String text, Integer idposta, LocalDateTime date){
        this.login=login;
        this.text=text;
        this.idposta=idposta;
        this.date=date;
    }

    public Komentarz() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Integer getIdposta() {
        return idposta;
    }

    public void setIdposta(Integer idposta) {
        this.idposta = idposta;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
