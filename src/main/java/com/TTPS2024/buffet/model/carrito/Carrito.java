package com.TTPS2024.buffet.model.carrito;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;
import com.TTPS2024.buffet.model.usuario.Alumno;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "carrito_menu",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menues;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "carrito_comida",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "comida_id")
    )
    private List<Comida> comidas;
    @OneToOne
    private Alumno usuario;

    public Carrito() {
        this.menues = new ArrayList<>();
        this.comidas = new ArrayList<>();
    }
    public void setUsuario(Alumno  alumno){
        this.usuario = alumno;
    }
    public Alumno getUsuario(){
        return this.usuario;
    }

    public boolean agregarProducto(Comida comida){
        return this.comidas.add(comida);
    }

    public boolean quitarProducto(Comida comida){
        return this.comidas.remove(comida);
    }
    public List<Comida> getComidas() {
        return this.comidas;
    }
    public boolean agregarProducto(Menu menu){
        return this.menues.add(menu);
    }

    public boolean quitarProducto(Menu menu){
        return this.menues.remove(menu);
    }
    public List<Menu> getMenues() {
        return this.menues;
    }
    public List<ProductoComercializable> getProductos(){
        List<ProductoComercializable> productos = new ArrayList<>();
        productos.addAll(this.comidas);
        productos.addAll(this.menues);
        return productos;
    }

    public Double getPrecioTotal(){
        return this.getPrecioComidas() + this.getPrecioMenues();
    }

    public Double getPrecioComidas(){
        return this.comidas.stream().mapToDouble(ProductoComercializable::getPrecio).sum();
    }
    public Double getPrecioMenues(){
        return this.menues.stream().mapToDouble(ProductoComercializable::getPrecio).sum();
    }

    public void setMenues(List<Menu> menues) {
        this.menues = menues;
    }

    public void setComidas(List<Comida> comidas) {
        this.comidas = comidas;
    }
}
