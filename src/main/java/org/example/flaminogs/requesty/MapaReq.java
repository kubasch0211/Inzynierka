package org.example.flaminogs.requesty;

public class MapaReq {
    private String login;
    private float latitude;
    private float longitude;
    private String status;

    public MapaReq() {
    }

    public MapaReq(final String login, final Float latitude, final Float longitude, final String status) {
        this.login=login;
        this.latitude=latitude;
        this.longitude=longitude;
        this.status=status;
    }

    public MapaReq(final String login, final String status) {
        this.login=login;
        this.status=status;
    }

    public MapaReq(final String login, final Float latitude, final Float longitude) {
        this.login=login;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
