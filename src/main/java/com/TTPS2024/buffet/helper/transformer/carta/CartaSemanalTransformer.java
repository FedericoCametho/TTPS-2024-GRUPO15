package com.TTPS2024.buffet.helper.transformer.carta;

import com.TTPS2024.buffet.controller.dto.carta.CartaSemanalDTO;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.model.carta.CartaSemanal;

import java.util.ArrayList;
import java.util.List;

public class CartaSemanalTransformer {
    public static CartaSemanalDTO toDTO(CartaSemanal cartaSemanal) {
        CartaSemanalDTO cartaSemanalDTO = new CartaSemanalDTO();
        cartaSemanalDTO.setNombre(cartaSemanal.getNombre());
        cartaSemanalDTO.setCartasDelDia(cartaSemanal.getCartas().stream().mapToLong(CartaDelDia::getId).boxed().toList());
        return cartaSemanalDTO;
    }

    public static List<CartaSemanalDTO> toDTOList(List<CartaSemanal> cartasSemanal) {
        return (cartasSemanal.isEmpty()) ? new ArrayList<>() :
                cartasSemanal.stream().map(CartaSemanalTransformer::toDTO).toList();
    }
}
