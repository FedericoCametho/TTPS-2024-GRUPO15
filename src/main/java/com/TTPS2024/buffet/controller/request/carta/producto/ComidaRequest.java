package com.TTPS2024.buffet.controller.request.carta.producto;

import com.TTPS2024.buffet.model.carta.producto.TipoComida;


public class ComidaRequest extends ProductoComercializableRequest {
    private TipoComida tipoComida;

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }


}
