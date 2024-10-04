package com.TTPS2024.buffet.model.carta.producto;

import com.TTPS2024.buffet.model.carrito.Compra;
import jakarta.persistence.*;

@MappedSuperclass
public interface ProductoComercializable {
    public Long getId();
    public String getNombre();
    public Double getPrecio();


}
