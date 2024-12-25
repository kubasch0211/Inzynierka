package org.example.flaminogs.requesty;

import java.util.List;

public class WiadomoscReq {
    private String token;
    private String loginreceiver;
    private String text;
    private List<String> multimedia;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<String> multimedia) {
        this.multimedia = multimedia;
    }

    public String getLoginreceiver() {
        return loginreceiver;
    }

    public void setLoginreceiver(String loginreceiver) {
        this.loginreceiver = loginreceiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public WiadomoscReq loginreceiver(String loginreceiver) {
        this.loginreceiver = loginreceiver;
        return this;
    }

    public WiadomoscReq loginsender(String loginsender) {
        this.token = loginsender;
        return this;
    }

    public WiadomoscReq text(String text) {
        this.text = text;
        return this;
    }

    public WiadomoscReq multimedia(List<String> multimedia) {
        this.multimedia = multimedia;
        return this;
    }
}
