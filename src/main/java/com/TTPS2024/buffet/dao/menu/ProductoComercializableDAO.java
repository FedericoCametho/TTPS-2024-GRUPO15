package com.TTPS2024.buffet.dao.menu;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;

import java.util.List;


public interface ProductoComercializableDAO<T extends ProductoComercializable> {

    List<T> findByNombre(String nombre);
    List<T> findByPrecio(Double precio);

}
