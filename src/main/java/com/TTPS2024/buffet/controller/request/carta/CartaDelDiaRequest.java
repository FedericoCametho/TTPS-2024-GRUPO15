package com.TTPS2024.buffet.controller.request.carta;

import com.TTPS2024.buffet.model.carta.CartaSemanal;
import com.TTPS2024.buffet.model.carta.DiaSemana;
import com.TTPS2024.buffet.model.carta.producto.Menu;

import java.util.List;


public class CartaDelDiaRequest {
    private List<Long> menues;
    private DiaSemana diaSemana;
    private boolean isActiva;


    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public List<Long> getMenues() {
        return menues;
    }
    public void setMenues(List<Long> menues) {
        this.menues = menues;
    }

    public boolean isActiva() {
        return isActiva;
    }

    public void setActiva(boolean activa) {
        isActiva = activa;
    }

}
