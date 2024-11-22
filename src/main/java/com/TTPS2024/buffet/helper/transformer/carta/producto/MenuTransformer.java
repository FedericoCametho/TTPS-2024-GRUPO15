package com.TTPS2024.buffet.helper.transformer.carta.producto;

import com.TTPS2024.buffet.controller.dto.carta.producto.ComidaDTO;
import com.TTPS2024.buffet.controller.dto.carta.producto.MenuDTO;
import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import com.TTPS2024.buffet.model.carta.producto.ProductoComercializable;

import java.util.ArrayList;
import java.util.List;


public class MenuTransformer {

    public static MenuDTO toDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        menuDTO.setNombre(menu.getNombre());
        menuDTO.setPrecio(menu.getPrecio());
        menuDTO.setFoto(menu.getFoto());
        menuDTO.setComidas(comidasToDto(menu.getComidas()));
        menuDTO.setCompras(menu.getCompras().stream().mapToLong(Compra::getId).boxed().toList());
        menuDTO.setCartasDelDia(menu.getCartasDelDia().stream().mapToLong(CartaDelDia::getId).boxed().toList());
        menuDTO.setVeggie(menu.isVeggie());
        return menuDTO;
    }

    private static List<ComidaDTO> comidasToDto(List<Comida> comidas) {
        List<ComidaDTO> comidasDTO = new ArrayList<>();
        for (Comida comida : comidas) {
            comidasDTO.add(ComidaTransformer.toDTO(comida));
        }
        return comidasDTO;
    }

    public static List<MenuDTO> toDTOList(List<Menu> menues) {
        return (menues.isEmpty()) ? new ArrayList<>() :
                menues.stream().map(MenuTransformer::toDTO).toList();
    }

}
