package com.example.projecto2matesandroid;

public class ItemAlumno {
    String id;
    String idAlumne;
    String name;
    String contrasena;
    String surname;
    String email;
    String rank;
    String lvl;
    String image;
    String id_classroom;

    public ItemAlumno(String id, String idAlumne, String name, String contrasena, String surname, String email, String rank, String lvl, String image, String id_classroom) {
        this.id = id;
        this.idAlumne = idAlumne;
        this.name = name;
        this.contrasena = contrasena;
        this.surname = surname;
        this.email = email;
        this.rank = rank;
        this.lvl = lvl;
        this.image = image;
        this.id_classroom = id_classroom;
    }
    public ItemAlumno(String name, String lvl, String image) {
        this.name = name;
        this.lvl = lvl;
        this.image = image;
    }
    public ItemAlumno(String name, String lvl) {
        this.name = name;
        this.lvl = lvl;
    }
    public ItemAlumno() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAlumne() {
        return idAlumne;
    }

    public void setIdAlumne(String idAlumne) {
        this.idAlumne = idAlumne;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getId_classroom() {
        return id_classroom;
    }

    public void setId_classroom(String id_classroom) {
        this.id_classroom = id_classroom;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
