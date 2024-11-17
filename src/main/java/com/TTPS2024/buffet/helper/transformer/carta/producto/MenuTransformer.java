package com.TTPS2024.buffet.helper.transformer.carta.producto;

import com.TTPS2024.buffet.controller.dto.MenuDTO;
import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
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
        menuDTO.setComidas(menu.getComidas().stream().mapToLong(ProductoComercializable::getId).boxed().toList());
        menuDTO.setCompras(menu.getCompras().stream().mapToLong(Compra::getId).boxed().toList());
        menuDTO.setCartasDelDia(menu.getCartasDelDia().stream().mapToLong(CartaDelDia::getId).boxed().toList());
        menuDTO.setVeggie(menu.isVeggie());
        return menuDTO;
    }

    public static List<MenuDTO> toDTOList(List<Menu> menues) {
        return (menues.isEmpty()) ? new ArrayList<>() :
                menues.stream().map(MenuTransformer::toDTO).toList();
    }

}
