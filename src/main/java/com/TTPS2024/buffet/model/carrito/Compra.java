package com.TTPS2024.buffet.model.carrito;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;
import com.TTPS2024.buffet.model.usuario.Alumno;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno usuario;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "compra_menu",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menues;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "compra_menu",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "comida_id")
    )
    private List<Comida> comidas;
    public Double importe;
    public Boolean pagado;

    public Compra() {

    }
    public Compra(Carrito carrito){
        this.fecha = LocalDateTime.now();
        this.usuario = carrito.getUsuario();
        this.menues = carrito.getMenues();
        this.comidas = carrito.getComidas();
        this.importe = carrito.getPrecioTotal();
        this.pagado = false;
    }

    public List<ProductoComercializable> getProductos(){
        List<ProductoComercializable> productos = new ArrayList<>();
        productos.addAll(this.comidas);
        productos.addAll(this.menues);
        return productos;
    }


}
