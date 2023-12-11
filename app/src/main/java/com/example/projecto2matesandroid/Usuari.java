package com.example.projecto2matesandroid;
import org.apache.commons.codec.digest.DigestUtils;
public class Usuari {

    private String id;
    private String usuariID;
    private String nickname;
    private String email;
    private String contrasena;

    public Usuari(){

    }


    public Usuari(String id, String usuariID, String nickname, String email, String password) {
        this.id = id;
        this.usuariID = usuariID;
        this.nickname = nickname;
        this.email = email;
        this.contrasena = password;
    }

    public Usuari(String id, String usuariID, String email) {
        this.id = id;
        this.usuariID = usuariID;
        this.email = email;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
