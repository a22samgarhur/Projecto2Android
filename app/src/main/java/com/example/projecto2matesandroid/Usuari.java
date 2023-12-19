package com.example.projecto2matesandroid;
import org.apache.commons.codec.digest.DigestUtils;
public class Usuari {

    private String id;
    private String usuariID;
    private String name;
    private String surname;
    private String email;
    private String image;
    private String contrasena;

    public Usuari(){

    }

    public Usuari(String id, String usuariID, String name, String surname, String email, String image, String contrasena) {
        this.id = id;
        this.usuariID = usuariID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.image = image;
        this.contrasena = contrasena;
    }



    public Usuari(String id, String usuariID, String email) {
        this.id = id;
        this.usuariID = usuariID;
        this.email = email;

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuariID() {
        return usuariID;
    }

    public void setUsuariID(String usuariID) {
        this.usuariID = usuariID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setContrasenaCifrada(String password){
        this.contrasena = DigestUtils.md5Hex(password).toUpperCase();
    }
}
