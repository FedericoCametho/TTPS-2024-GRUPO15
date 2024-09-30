package com.TTPS2024.buffet.model.carrito;

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
            name = "carrito_productos",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductoComercializable> productos;
    @OneToOne
    private Alumno usuario;

    public Carrito() {
        this.productos = new ArrayList<>();
    }
    public void setUsuario(Alumno  alumno){
        this.usuario = alumno;
    }
    public Alumno getUsuario(){
        return this.usuario;
    }

    public boolean agregarProducto(ProductoComercializable producto){
        return this.productos.add(producto);
    }

    public boolean quitarProducto(ProductoComercializable producto){
        return this.productos.remove(producto);
    }
    public List<ProductoComercializable> getProductos() {
        return productos;
    }

    public Double getPrecioTotal(){
        return this.productos.stream().mapToDouble(ProductoComercializable::getPrecio).sum();
    }

}
