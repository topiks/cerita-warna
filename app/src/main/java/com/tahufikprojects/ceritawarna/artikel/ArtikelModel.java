package com.tahufikprojects.ceritawarna.artikel;

public class ArtikelModel {
    String judul;
    String isi;

    public ArtikelModel()
    {
    }

    public String getJudul()
    {
        return this.judul;
    }

    public String getIsi()
    {
        return this.isi;
    }

    public void setJudul(String judulBaru)
    {
        this.judul = judulBaru;
    }

    public void setIsi(String isiBaru)
    {
        this.isi  = isiBaru;
    }
}
