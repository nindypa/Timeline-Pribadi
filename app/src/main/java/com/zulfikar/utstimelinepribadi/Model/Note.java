package com.zulfikar.utstimelinepribadi.Model;

public class Note {
    private String judul;
    private String deskripsi;
    private String date;

    public Note(String judul, String deskripsi, String date) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.date = date;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
