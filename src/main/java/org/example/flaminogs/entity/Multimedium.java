package org.example.flaminogs.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "multimedia")
public class Multimedium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idpostu;

    private Integer idkomentarza;

    private Integer idwiadomosci;

    @Column(name = "Multimedium", nullable = false, length = 255)
    private String multimedium;

    public Multimedium(Integer idpostu, Integer idkomentarza, Integer idwiadomosci, String multimedium){
        this.multimedium=multimedium;
        this.idkomentarza=idkomentarza;
        this.idpostu=idpostu;
        this.idwiadomosci=idwiadomosci;
    }

    public Multimedium() {

    }

    public Integer getIdkomentarza() {
        return idkomentarza;
    }

    public void setIdkomentarza(int idKomentarza) {
        this.idkomentarza = idKomentarza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdpostu() {
        return idpostu;
    }

    public void setIdpostu(Integer idPostu) {
        this.idpostu = idPostu;
    }

    public Integer getIdwiadomosci() {
        return idwiadomosci;
    }

    public void setIdwiadomosci(Integer idWiadomosci) {
        this.idwiadomosci = idWiadomosci;
    }

    public String getMultimedium() {
        return multimedium;
    }

    public void setMultimedium(String multimedium) {
        this.multimedium = multimedium;
    }

}
