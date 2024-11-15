package com.TTPS2024.buffet.helper.transformer;

import com.TTPS2024.buffet.controller.dto.ProductoComercializableDTO;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;

public interface ProductoComercializableTransformer<T extends ProductoComercializableDTO, S extends ProductoComercializable> {

    public T toDTO(S productoComercializable);


}
