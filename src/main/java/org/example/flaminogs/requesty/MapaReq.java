package org.example.flaminogs.requesty;

public class MapaReq {
    private String token;
    private float latitude;
    private float longitude;
    private String status;

    public MapaReq() {
    }

    public MapaReq(final String token, final Float latitude, final Float longitude, final String status) {
        this.token = token;
        this.latitude=latitude;
        this.longitude=longitude;
        this.status=status;
    }

    public MapaReq(final String token, final String status) {
        this.token = token;
        this.status=status;
    }

    public MapaReq(final String token, final Float latitude, final Float longitude) {
        this.token = token;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
