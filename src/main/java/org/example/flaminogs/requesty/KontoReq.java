package org.example.flaminogs.requesty;
import jakarta.validation.constraints.*;

public class KontoReq {
    @Size(min = 5, max = 30, message = "Login musi mieć od 5 do 30 znaków.")
    private String login;
    @Size(min = 5, max = 32, message = "Hasło musi mieć od 5 do 32 znaków.")
    private String password;
    @Size(min = 1, max = 60, message = "Nick musi mieć od 1 do 60 znaków.")
    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
