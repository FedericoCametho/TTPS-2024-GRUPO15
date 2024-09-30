package com.TTPS2024.buffet.model.carta.producto;

import com.TTPS2024.buffet.model.carrito.Carrito;
import com.TTPS2024.buffet.model.carrito.Compra;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Menu extends ProductoComercializable {

    @ManyToMany(mappedBy = "menues", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comida> comidas;

    @ManyToMany
    @JoinTable(
            name = "menu_carrito",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "carrito_id")
    )
    private List<Carrito> carritos;
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    public Menu(String titulo, List<Comida> comidas, Double precio) {
        super(titulo, precio);
        this.comidas = comidas;
        this.setComidasEnMenu(comidas);
    }

    public Menu() {
        super();
    }

    private void setComidasEnMenu(List<Comida> comidas) {
        comidas.forEach(comida -> comida.setEnMenu(Boolean.TRUE));
    }

}
