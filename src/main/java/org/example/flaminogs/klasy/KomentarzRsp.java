package org.example.flaminogs.klasy;

import org.example.flaminogs.entity.Komentarz;
import org.example.flaminogs.entity.Multimedium;

import java.util.List;

public class KomentarzRsp {
    private Komentarz komentarz;
    private List<String> multimedia;

    public KomentarzRsp(Komentarz komentarz, List<String> multimedia) {
        this.komentarz = komentarz;
        this.multimedia = multimedia;
    }

    public KomentarzRsp() {
    }

    public Komentarz getKomentarz() {
        return komentarz;
    }

    public void setKomentarz(Komentarz komentarz) {
        this.komentarz = komentarz;
    }

    public List<String> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<String> multimedia) {
        this.multimedia = multimedia;
    }
}
