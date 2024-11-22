package com.TTPS2024.buffet.helper.transformer.carta;

import com.TTPS2024.buffet.controller.dto.carta.CartaDelDiaDTO;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;

import java.util.ArrayList;
import java.util.List;

public class CartaDelDiaTransformer {

    public static CartaDelDiaDTO toDTO(CartaDelDia cartaDelDia) {
        CartaDelDiaDTO cartaDelDiaDTO = new CartaDelDiaDTO();
        cartaDelDiaDTO.setMenus(cartaDelDia.getMenues().stream().mapToLong(ProductoComercializable::getId).boxed().toList());
        cartaDelDiaDTO.setDiaSemana(cartaDelDia.getDiaSemana());
        cartaDelDiaDTO.setActiva(cartaDelDia.isActiva());
        return cartaDelDiaDTO;
    }

    public static List<CartaDelDiaDTO> toDTOList(List<CartaDelDia> cartaDelDias) {
        return (cartaDelDias.isEmpty()) ? new ArrayList<>() :
                cartaDelDias.stream().map(CartaDelDiaTransformer::toDTO).toList();
    }
}
