package com.example.projecto2matesandroid;

public class ItemHome {

    private String id;
    private String  professor_id;
    private String name;
    private String access_code;

    public ItemHome(String  id, String name) {
        this.id = id;
        this.name = name;
    }

    public ItemHome(String  id, String  professor_id, String name, String access_code) {
        this.id = id;
        this.professor_id = professor_id;
        this.name = name;
        this.access_code = access_code;
    }

    public ItemHome(String  id, String name, String access_code) {
        this.id = id;
        this.name = name;
        this.access_code = access_code;
    }


    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String  getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(String  professor_id) {
        this.professor_id = professor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccess_code() {
        return access_code;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }
}
