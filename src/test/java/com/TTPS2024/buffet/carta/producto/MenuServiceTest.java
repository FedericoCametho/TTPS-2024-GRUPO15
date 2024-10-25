package com.TTPS2024.buffet.carta.producto;

import com.TTPS2024.buffet.AbstractGenericTest;

import com.TTPS2024.buffet.controller.request.carta.producto.MenuRequest;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import org.junit.jupiter.api.*;


import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MenuServiceTest extends AbstractGenericTest {
    @Test
    @Order(1)
    public void testGetAllMenus() {
        List<Menu> menus = this.menuService.getAll();
        assertNotNull(menus);
        assertEquals(10, menus.size());
    }

    @Test
    @Order(2)
    public void testCreateMenu() {
        MenuRequest menuRequest = this.createMenuRequest("Menu Lunes EXTRA", 6000.0, List.of(
                this.comidaService.getProductsByName("Milanesa").get(0),
                this.comidaService.getProductsByName("Ensalada").get(0),
                this.comidaService.getProductsByName("Helado").get(0)
        ),false);
        Menu menu = this.menuService.save(menuRequest);
        this.testQueryAndValidateMenuById(menu.getId(), menuRequest);
    }
    private void testQueryAndValidateMenuById(Long id, MenuRequest menuRequest) {
        Menu menu = this.menuService.getProductById(id);
        assertNotNull(menu);
        assertEquals(menuRequest.getNombre(), menu.getNombre());
        assertEquals(menuRequest.getPrecio(), menu.getPrecio());
        assertEquals(menuRequest.getComidas().size(), menu.getComidas().size());
        assertEquals(menuRequest.getImagen(), menu.getFoto());
    }



    @Test
    @Order(3)
    public void testGetByNombre() {
        List<Menu> menus = this.menuService.getProductsByName("No existe");
        assertNotNull(menus);
        assertEquals(0, menus.size());

        menus = this.menuService.getProductsByName("Menu Lunes Comun");
        assertNotNull(menus);
        assertEquals(1, menus.size());
        Menu menu = menus.get(0);
        assertEquals("Menu Lunes Comun", menu.getNombre());
        assertEquals(4500.0, menu.getPrecio());
        assertEquals(3, menu.getComidas().size());
    }

    @Test
    @Order(4)
    public void testUpdateMenu(){
        List<Menu> menus = this.menuService.getProductsByName("Menu Lunes Comun");
        assertNotNull(menus);
        assertEquals(1, menus.size());
        Menu menu = menus.get(0);
        MenuRequest menuRequestUpdate = this.createMenuRequest(menu.getNombre(), 9000.0,menu.getComidas(), menu.isVeggie());
        this.menuService.update(menu.getId(), menuRequestUpdate);
        this.testQueryAndValidateMenuById(menu.getId(), menuRequestUpdate);
    }

    @Test
    @Order(5)
    public void testGetByPrecio() {
        List<Menu> menus = this.menuService.getProductsByPrice(Double.valueOf("0.00"));
        assertNotNull(menus);
        assertEquals(0, menus.size());

        menus = this.menuService.getProductsByPrice(Double.valueOf("3500.00"));
        assertNotNull(menus);
        assertEquals(5, menus.size());
    }


}
