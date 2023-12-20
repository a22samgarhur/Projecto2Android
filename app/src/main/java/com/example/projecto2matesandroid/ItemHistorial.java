package com.example.projecto2matesandroid;

public class ItemHistorial {

    String hora;
    String historial;

    public ItemHistorial() {

    }

    public ItemHistorial(String Historial,String hora) {
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
