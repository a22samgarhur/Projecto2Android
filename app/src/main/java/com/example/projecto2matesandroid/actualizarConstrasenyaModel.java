package com.example.projecto2matesandroid;

import org.apache.commons.codec.digest.DigestUtils;

public class actualizarConstrasenyaModel {
    String contrasenyaAntigua;
    String contrasenyaNueva;

    public actualizarConstrasenyaModel() {

    }

    public actualizarConstrasenyaModel(String contrasenyaAntigua, String contrasenyaNueva) {
        this.contrasenyaAntigua = DigestUtils.md5Hex(contrasenyaAntigua).toUpperCase();
        this.contrasenyaNueva = DigestUtils.md5Hex(contrasenyaNueva).toUpperCase();
    }

    public String getContrasenyaAntigua() {
        return contrasenyaAntigua;
    }

    public void setContrasenyaAntigua(String contrasenyaAntigua) {
        this.contrasenyaAntigua = DigestUtils.md5Hex(contrasenyaAntigua).toUpperCase();
    }

    public String getContrasenyaNueva() {
        return contrasenyaNueva;
    }

    public void setContrasenyaNueva(String contrasenyaNueva) {
        this.contrasenyaNueva = DigestUtils.md5Hex(contrasenyaNueva).toUpperCase();
    }
}
