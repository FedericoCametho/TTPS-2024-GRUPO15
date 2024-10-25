package com.TTPS2024.buffet.controller.request.carta.producto;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import java.util.List;

public class MenuRequest extends ProductoComercializableRequest {

    private List<Comida> comidas;
    private boolean isVeggie;

    public boolean isVeggie() {
        return isVeggie;
    }

    public void setVeggie(boolean veggie) {
        isVeggie = veggie;
    }

    public List<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(List<Comida> comidas) {
        this.comidas = comidas;
    }
}
