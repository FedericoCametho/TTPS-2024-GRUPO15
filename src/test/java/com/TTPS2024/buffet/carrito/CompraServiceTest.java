package com.TTPS2024.buffet.carrito;


import com.TTPS2024.buffet.AbstractGenericTest;
import com.TTPS2024.buffet.model.carrito.Carrito;
import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import com.TTPS2024.buffet.model.usuario.Alumno;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompraServiceTest extends AbstractGenericTest {

    @ParameterizedTest
    @MethodSource("createCarritoWithData")
    @Order(1)
    public void testCreateCompra(Carrito carrito) {
        Compra compra = this.compraService.save(carrito);
        this.testQueryAndValidateCompraById(compra.getId(), carrito);
    }
    private void testQueryAndValidateCompraById(Long idCompra, Carrito carrito) {
        Compra compra = this.compraService.getById(idCompra);
        assertNotNull(compra);
        assertEquals(compra.getComidas().size(), carrito.getComidas().size());
        assertEquals(compra.getMenues().size(), carrito.getMenues().size());
        assertEquals(compra.getImporte(), carrito.getPrecioTotal());
        assertEquals(compra.getUsuario().getId(), carrito.getUsuario().getId());
        assertFalse(compra.isPagado());
    }

    @Test
    @Order(2)
    public void testGetAllComprasByAlumno() {
        Alumno alumno = this.alumnoService.getUserByDni(1111111);
        List<Compra> compras = this.compraService.getComprasByAlumno(alumno);
        assertNotNull(compras);
        assertEquals(compras.size(), 1);
    }

    @Test
    @Order(3)
    public void testGetAllCompras() {
        List<Compra> compras = this.compraService.getAll();
        assertNotNull(compras);
        assertEquals(compras.size(), 2);
    }

    @Test
    @Order(4)
    public void testPagarCompra() {
        Alumno alumno = this.alumnoService.getUserByDni(1111111);
        List<Compra> compras = this.compraService.getComprasByAlumno(alumno);
        assertNotNull(compras);
        assertEquals(compras.size(), 1);
        Compra compra = compras.get(0);
        this.compraService.pagarCompra(compra.getId());
        compra = this.compraService.getById(compra.getId());
        assertTrue(compra.isPagado());
    }

    @Test
    @Order(5)
    public void testGetByPrecio() {
        List<Compra> compras = this.compraService.getComprasPrecioMayorQue(12000.0);
        assertNotNull(compras);
        assertEquals(compras.size(), 1);

        compras = this.compraService.getComprasPrecioMenorQue(12000.0);
        assertNotNull(compras);
        assertEquals(compras.size(), 1);

    }

    @Test
    @Order(6)
    public void testRevertirCompra() {
        Alumno alumno = this.alumnoService.getUserByDni(1111111);
        List<Compra> compras = this.compraService.getComprasByAlumno(alumno);
        assertNotNull(compras);
        assertEquals(compras.size(), 1);
        Compra compra = compras.get(0);
        this.compraService.marcarImpagoCompra(compra.getId());
        compra = this.compraService.getById(compra.getId());
        assertFalse(compra.isPagado());
    }


    private Carrito createCarrito(Alumno alumno, List<Menu> menues, List<Comida> comidas) {
        Carrito carrito = new Carrito();
        carrito.setUsuario(alumno);
        carrito.setMenues(menues);
        carrito.setComidas(comidas);
        return carrito;
    }

    private Stream<Arguments> createCarritoWithData() {
        return Stream.of(
                Arguments.of(this.createCarrito(this.alumnoService.getUserByDni(1111111), List.of(
                        this.menuService.getProductsByName("Menu Lunes Comun").get(0),
                        this.menuService.getProductsByName("Menu Martes Comun").get(0)
                ), List.of(
                        this.comidaService.getProductsByName("Milanesa").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0)
                ))),
                Arguments.of(this.createCarrito(this.alumnoService.getUserByDni(22222222), List.of(
                        this.menuService.getProductsByName("Menu Lunes Vegano").get(0),
                        this.menuService.getProductsByName("Menu Martes Vegano").get(0)
                ), List.of(
                        this.comidaService.getProductsByName("Pasta").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0)
                )))
        );
    }




}
