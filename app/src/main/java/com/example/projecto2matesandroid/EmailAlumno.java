package com.example.projecto2matesandroid;

public class EmailAlumno {

    String email;
    int id;

    public EmailAlumno() {
    }

    public EmailAlumno(String email,String id) {

        this.email = email;
        this.id=Integer.parseInt(id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
