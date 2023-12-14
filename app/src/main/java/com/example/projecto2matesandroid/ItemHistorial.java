package com.example.projecto2matesandroid;

public class ItemHistorial {

    String Hora;
    String informacion;

    public ItemHistorial() {

    }

    public ItemHistorial(String hora, String informacion) {
        Hora = hora;
        this.informacion = informacion;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }


}
