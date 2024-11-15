package com.TTPS2024.buffet.dao.carta.producto;

import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;

import java.util.List;


public interface ProductoComercializableDAO<T extends ProductoComercializable> {

    List<T> findByNombreContaining(String nombre);
    List<T> findByPrecio(Double precio);

}
