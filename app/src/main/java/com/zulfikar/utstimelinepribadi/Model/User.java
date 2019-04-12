package com.zulfikar.utstimelinepribadi.Model;

public class User {
    private String username;
    private String password;

    public User(String nama, String password) {
        this.username = nama;
        this.password = password;
    }

    public String getNama() {
        return username;
    }

    public void setNama(String nama) {
        this.username = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
