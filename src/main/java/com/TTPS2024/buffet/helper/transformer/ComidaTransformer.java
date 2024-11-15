package com.TTPS2024.buffet.helper.transformer;

import com.TTPS2024.buffet.controller.dto.ComidaDTO;
import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;
import org.springframework.stereotype.Component;


@Component
public class ComidaTransformer implements ProductoComercializableTransformer<ComidaDTO, Comida> {
    @Override
    public ComidaDTO toDTO(Comida comida) {
        ComidaDTO comidaDTO = new ComidaDTO();
        comidaDTO.setId(comida.getId());
        comidaDTO.setNombre(comida.getNombre());
        comidaDTO.setPrecio(comida.getPrecio());
        comidaDTO.setTipoComida(comida.getTipoComida());
        comidaDTO.setFoto(comida.getFoto());
        comidaDTO.setMenues(comida.getMenues().stream().mapToLong(ProductoComercializable::getId).boxed().toList());
        comidaDTO.setCompras(comida.getCompras().stream().mapToLong(Compra::getId).boxed().toList());
        comidaDTO.setEnMenu(comida.getEnMenu() != null);
        return comidaDTO;
    }
}
