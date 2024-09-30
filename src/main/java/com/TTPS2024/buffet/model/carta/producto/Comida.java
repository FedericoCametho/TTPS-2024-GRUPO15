package com.TTPS2024.buffet.model.carta.producto;

import com.TTPS2024.buffet.model.carrito.Carrito;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Comida extends ProductoComercializable{


    private TipoComida tipoComida;
    @OneToMany
    private List<EtiquetaComida> etiquetas;
    @ManyToMany
    @JoinTable(
            name = "comida_menu",
            joinColumns = @JoinColumn(name = "comida_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menues;

    @ManyToMany
    @JoinTable(
            name = "comida_carrito",
            joinColumns = @JoinColumn(name = "comida_id"),
            inverseJoinColumns = @JoinColumn(name = "carrito_id")
    )
    private List<Carrito> carritos;

    private Boolean enMenu;

    public Comida(String nombre, TipoComida tipoComida, Double precio, List<EtiquetaComida> etiquetas) {
        super(nombre, precio);
        this.tipoComida = tipoComida;
        this.etiquetas = etiquetas;
        this.enMenu = false;
    }

    public Comida() {
        super();
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public List<EtiquetaComida> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<EtiquetaComida> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public List<Menu> getMenues() {
        return menues;
    }

    public void setMenues(List<Menu> menues) {
        this.menues = menues;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }

    public boolean isEnMenu() {
        return enMenu;
    }

    public void setEnMenu(Boolean enMenu) {
        this.enMenu = enMenu;
    }
}
