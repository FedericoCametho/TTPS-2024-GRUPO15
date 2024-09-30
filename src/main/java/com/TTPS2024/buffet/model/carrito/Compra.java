package com.TTPS2024.buffet.model.carrito;

import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;
import com.TTPS2024.buffet.model.usuario.Alumno;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductoComercializable> productos;
    public Double importe;
    public Boolean pagado;

    public Compra() {

    }
    public Compra(Carrito carrito){
        this.fecha = LocalDateTime.now();
        this.usuario = carrito.getUsuario();
        this.productos = carrito.getProductos();
        this.importe = carrito.getPrecioTotal();
        this.pagado = false;
    }


}
