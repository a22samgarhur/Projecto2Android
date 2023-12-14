package com.example.projecto2matesandroid;

public class ItemHistorial {

    String Hora;
    String informacion;
    String correcta;

    public ItemHistorial() {

    }

    public ItemHistorial(String hora, String informacion, String correcta) {
        Hora = hora;
        this.informacion = informacion;
        this.correcta = correcta;
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

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }
}
