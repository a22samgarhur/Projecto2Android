package com.example.projecto2matesandroid;

public class ItemHistorial {

    String historial;
    String hora;


    public ItemHistorial() {

    }

    public ItemHistorial(String hora,String Historial) {
        this.hora = hora;
        this.historial = Historial;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }


}
