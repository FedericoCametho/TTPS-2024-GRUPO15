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
        Menu menuComunRequest = this.menuService.getProductById(cartaDelDiaRequest.getMenues().get(0));
        Menu menuVegetarianoRequest = this.menuService.getProductById(cartaDelDiaRequest.getMenues().get(1));
        assertEquals(menuComunRequest.getId(), cartaDelDia.getMenuComun().getId());
        assertEquals(menuVegetarianoRequest.getId(), cartaDelDia.getMenuVegetariano().getId());
        assertEquals(cartaDelDiaRequest.getDiaSemana(), cartaDelDia.getDiaSemana());
    }


    @Test
    @Order(2)
    public void testUpdateCartaDelDia() {
        CartaDelDia cartaDelDia = this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.LUNES);
        CartaDelDiaRequest cartaDelDiaRequest = new CartaDelDiaRequest();
        cartaDelDiaRequest.setMenues(List.of(this.menuService.getProductsByName("Menu Martes Comun").get(0).getId(),this.menuService.getProductsByName("Menu Martes Vegano").get(0).getId()));
        cartaDelDiaRequest.setDiaSemana(DiaSemana.MARTES);
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