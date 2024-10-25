package com.TTPS2024.buffet.model.carta.producto;

import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends ProductoComercializable {

    @ManyToMany(mappedBy = "menues", fetch = FetchType.EAGER)
    private List<Comida> comidas;
    @ManyToMany(mappedBy = "menues", fetch = FetchType.EAGER)
    private List<Compra> compras;
    @ManyToMany(mappedBy = "menues", fetch = FetchType.EAGER)
    private List<CartaDelDia> cartasDelDia;
    private boolean isVeggie;


    public Menu(String titulo, Double precio, List<Comida> comidas, byte[] foto, boolean isVeggie) {
        super(titulo, precio, foto);
        this.comidas = comidas;
        this.compras = new ArrayList<>();
        this.cartasDelDia = new ArrayList<>();
        this.isVeggie = isVeggie;
    }

    public Menu() {
        super();
    }


    public List<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(List<Comida> comidas) {
        this.comidas = comidas;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public List<CartaDelDia> getCartasDelDia() {
        return cartasDelDia;
    }

    public void setCartasDelDia(List<CartaDelDia> cartasDelDia) {
        this.cartasDelDia = cartasDelDia;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public boolean hasComida(Comida comida){
        return this.comidas.contains(comida);
    }

    public boolean isVeggie() {
        return isVeggie;
    }

    public void setVeggie(boolean veggie) {
        isVeggie = veggie;
    }
}
