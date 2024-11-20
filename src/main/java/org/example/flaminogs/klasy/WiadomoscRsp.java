package org.example.flaminogs.klasy;

import org.example.flaminogs.entity.Wiadomosc;

import java.util.List;

public class WiadomoscRsp {
    private Wiadomosc wiadomosc;
    private List<String> multimedia;

    public WiadomoscRsp(final Wiadomosc wiadomosc, final List<String> multimedia) {
        this.wiadomosc = wiadomosc;
        this.multimedia = multimedia;
    }

    public WiadomoscRsp() {

    }

    public Wiadomosc getWiadomosc() {
        return wiadomosc;
    }

    public void setWiadomosc(Wiadomosc wiadomosc) {
        this.wiadomosc = wiadomosc;
    }

    public List<String> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<String> multimedia) {
        this.multimedia = multimedia;
    }
}
