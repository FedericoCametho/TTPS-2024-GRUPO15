package com.TTPS2024.buffet.helper.transformer.carta.producto;

import com.TTPS2024.buffet.controller.dto.ComidaDTO;
import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;

import java.util.ArrayList;
import java.util.List;


public class ComidaTransformer  {

    public static ComidaDTO toDTO(Comida comida) {
        ComidaDTO comidaDTO = new ComidaDTO();
        comidaDTO.setId(comida.getId());
        comidaDTO.setNombre(comida.getNombre());
        comidaDTO.setPrecio(comida.getPrecio());
        comidaDTO.setTipoComida(comida.getTipoComida());
        comidaDTO.setFoto(comida.getFoto());
        comidaDTO.setMenues(comida.getMenues().stream().mapToLong(ProductoComercializable::getId).boxed().toList());
        comidaDTO.setCompras(comida.getCompras().stream().mapToLong(Compra::getId).boxed().toList());
        comidaDTO.setEnMenu(comida.getEnMenu());
        return comidaDTO;
    }

    public static List<ComidaDTO> toDTOList(List<Comida> comidas) {
        return (comidas.isEmpty()) ? new ArrayList<>() :
                comidas.stream().map(ComidaTransformer::toDTO).toList();
    }
}
