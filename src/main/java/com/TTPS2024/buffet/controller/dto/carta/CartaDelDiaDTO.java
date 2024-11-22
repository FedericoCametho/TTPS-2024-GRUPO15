package com.TTPS2024.buffet.controller.dto.carta;

import com.TTPS2024.buffet.controller.dto.carta.producto.MenuDTO;
import com.TTPS2024.buffet.model.carta.CartaSemanal;
import com.TTPS2024.buffet.model.carta.DiaSemana;
import com.TTPS2024.buffet.model.carta.producto.Menu;

import java.util.List;

public class CartaDelDiaDTO {
    private List<MenuDTO> menus;
    private DiaSemana diaSemana;
    private boolean isActiva;

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public boolean isActiva() {
        return isActiva;
    }

    public void setActiva(boolean activa) {
        isActiva = activa;
    }
}
