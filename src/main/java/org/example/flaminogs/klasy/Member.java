package org.example.flaminogs.klasy;

public class Member {
    private String login;
    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member login(String login) {
        this.login = login;
        return this;
    }

    public Member name(String name) {
        this.name = name;
        return this;
    }

}
