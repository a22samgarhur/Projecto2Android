package com.example.projecto2matesandroid;

public class ItemAlumno {
    String nom;
    String nivel;
    int imagen;

    public ItemAlumno(String nom, String nivel, int imagen) {
        this.nom = nom;
        this.nivel = nivel;
        this.imagen = imagen;
    }
    public ItemAlumno(String nom, String nivel) {
        this.nom = nom;
        this.nivel = nivel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
