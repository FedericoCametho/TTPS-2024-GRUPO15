package com.TTPS2024.buffet.carta;


import com.TTPS2024.buffet.AbstractGenericTest;
import com.TTPS2024.buffet.controller.request.carta.CartaDelDiaRequest;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.model.carta.DiaSemana;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import org.junit.jupiter.api.*;


import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartaDelDiaServiceTest extends AbstractGenericTest {


    @Test
    @Order(1)
    public void testGetAllCartasDelDia() {
        List<CartaDelDia> cartasDelDia = this.cartaDelDiaService.getAll();
        assertNotNull(cartasDelDia);
        assertEquals(5, cartasDelDia.size());
    }


    private void testQueryAndValidateCartaDelDiaById(Long id, CartaDelDiaRequest cartaDelDiaRequest) {
        CartaDelDia cartaDelDia = this.cartaDelDiaService.getById(id);
        assertNotNull(cartaDelDia);
        Menu menuComunRequest = this.menuService.getProductById(cartaDelDiaRequest.getMenuComun().getId());
        Menu menuVegetarianoRequest = this.menuService.getProductById(cartaDelDiaRequest.getMenuVegetariano().getId());
        assertEquals(menuComunRequest.getId(), cartaDelDia.getMenuComun().getId());
        assertEquals(menuVegetarianoRequest.getId(), cartaDelDia.getMenuVegetariano().getId());
        assertEquals(cartaDelDiaRequest.getDiaSemana(), cartaDelDia.getDiaSemana());
        assertEquals(cartaDelDia.getCartaSemanal().getId(), cartaDelDiaRequest.getCartaSemanal().getId());
    }


    @Test
    @Order(2)
    public void testUpdateCartaDelDia() {
        CartaDelDia cartaDelDia = this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.LUNES).get(0);
        CartaDelDiaRequest cartaDelDiaRequest = new CartaDelDiaRequest();
        cartaDelDiaRequest.setMenues(List.of(this.menuService.getProductsByName("Menu Martes Comun").get(0),this.menuService.getProductsByName("Menu Martes Vegano").get(0)));
        cartaDelDiaRequest.setDiaSemana(DiaSemana.MARTES);
        cartaDelDiaRequest.setCartaSemanal(cartaDelDia.getCartaSemanal());
        this.cartaDelDiaService.update(cartaDelDia.getId(), cartaDelDiaRequest);
        this.testQueryAndValidateCartaDelDiaById(cartaDelDia.getId(), cartaDelDiaRequest);
    }

    @Test
    @Order(6)
    public void testDeleteCartaDelDiaById() {
        List<CartaDelDia> cartasDelDia = this.cartaDelDiaService.getAll();
        assertNotNull(cartasDelDia);
        assertEquals(5, cartasDelDia.size());
        this.cartaDelDiaService.delete(cartasDelDia.get(0).getId());
        cartasDelDia = this.cartaDelDiaService.getAll();
        assertNotNull(cartasDelDia);
        assertEquals(4, cartasDelDia.size());
    }




}