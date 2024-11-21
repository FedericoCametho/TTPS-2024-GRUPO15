package com.TTPS2024.buffet.controller.request.carta.producto;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import java.util.List;

public class MenuRequest extends ProductoComercializableRequest {

    private List<Long> comidas; // Listado de ID

    private boolean isVeggie;

    public boolean isVeggie() {
        return isVeggie;
    }

    public void setVeggie(boolean veggie) {
        isVeggie = veggie;
    }

    public List<Long> getComidas() {
        return comidas;
    }

    public void setComidas(List<Long> comidas) {
        this.comidas = comidas;
    }
}
