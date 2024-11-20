package org.example.flaminogs.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "lokalizacjeistatusy")
public class LokalizacjaIStatus {
    @Id
    @Column(name = "login", nullable = false, length = 30)
    private String login;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "status", length = 255)
    private String status;

    private LocalDateTime datestatus;

    private LocalDateTime datelocation;


    public LokalizacjaIStatus(String login) {
        this.login = login;
    }

    public LokalizacjaIStatus() {

    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDatestatus() {
        return datestatus;
    }

    public void setDatestatus(LocalDateTime dateStatus) {
        this.datestatus = dateStatus;
    }

    public LocalDateTime getDatelocation() {
        return datelocation;
    }

    public void setDatelocation(LocalDateTime dateLocation) {
        this.datelocation = dateLocation;
    }

}
