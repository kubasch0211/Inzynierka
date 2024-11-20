package org.example.flaminogs.klasy;

import org.example.flaminogs.entity.LokalizacjaIStatus;

public class MapaRsp {
    private LokalizacjaIStatus lokalizacjaIStatus;
    private String nazwa;

    public MapaRsp(LokalizacjaIStatus lokalizacjaIStatus, String nazwa){
        this.lokalizacjaIStatus=lokalizacjaIStatus;
        this.nazwa=nazwa;
    }
    public LokalizacjaIStatus getLokalizacjaIStatus() {
        return lokalizacjaIStatus;
    }

    public void setLokalizacjaIStatus(LokalizacjaIStatus lokalizacjaIStatus) {
        this.lokalizacjaIStatus = lokalizacjaIStatus;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
