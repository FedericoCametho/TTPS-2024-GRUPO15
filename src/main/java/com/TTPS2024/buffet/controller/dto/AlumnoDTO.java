package com.TTPS2024.buffet.controller.dto;

import jakarta.persistence.Lob;


import java.util.List;

public class AlumnoDTO extends UsuarioDTO{
    private List<Long> compras;
    private Boolean habilitado;
    @Lob
    private byte[] fotoDePerfil;

    private List<Long> sugerencias;

    public List<Long> getCompras() {
        return compras;
    }

    public void setCompras(List<Long> compras) {
        this.compras = compras;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public byte[] getFotoDePerfil() {
        return fotoDePerfil;
    }

    public void setFotoDePerfil(byte[] fotoDePerfil) {
        this.fotoDePerfil = fotoDePerfil;
    }

    public List<Long> getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(List<Long> sugerencias) {
        this.sugerencias = sugerencias;
    }
}
