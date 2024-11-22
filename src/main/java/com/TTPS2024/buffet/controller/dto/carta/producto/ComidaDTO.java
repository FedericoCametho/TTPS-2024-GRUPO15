package com.TTPS2024.buffet.controller.dto.carta.producto;


import com.TTPS2024.buffet.model.carta.producto.TipoComida;


import java.util.List;

public class ComidaDTO extends ProductoComercializableDTO{
    private TipoComida tipoComida;
    private List<Long> menues;
    private List<Long> compras;
    private Boolean enMenu;

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public List<Long> getMenues() {
        return menues;
    }

    public void setMenues(List<Long> menues) {
        this.menues = menues;
    }

    public List<Long> getCompras() {
        return compras;
    }

    public void setCompras(List<Long> compras) {
        this.compras = compras;
    }

    public Boolean getEnMenu() {
        return enMenu;
    }

    public void setEnMenu(Boolean enMenu) {
        this.enMenu = enMenu;
    }
}
