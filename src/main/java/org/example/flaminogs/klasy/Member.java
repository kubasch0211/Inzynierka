package org.example.flaminogs.klasy;

public class Member {
    private String login;
    private String name;
    private boolean isadmin;

    public boolean isIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

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

    public Member isadmin(boolean isadmin) {
        this.isadmin = isadmin;
        return this;
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
