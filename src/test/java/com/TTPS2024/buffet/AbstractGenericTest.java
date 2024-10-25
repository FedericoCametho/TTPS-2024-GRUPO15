package com.TTPS2024.buffet;

import com.TTPS2024.buffet.controller.request.carta.CartaDelDiaRequest;
import com.TTPS2024.buffet.controller.request.carta.CartaSemanalRequest;
import com.TTPS2024.buffet.controller.request.carta.producto.ComidaRequest;
import com.TTPS2024.buffet.controller.request.carta.producto.MenuRequest;
import com.TTPS2024.buffet.controller.request.sugerencia.SugerenciaRequest;
import com.TTPS2024.buffet.controller.request.usuario.AlumnoRequest;
import com.TTPS2024.buffet.dao.carrito.CompraDAO;
import com.TTPS2024.buffet.dao.carta.CartaDelDiaDAO;
import com.TTPS2024.buffet.dao.carta.CartaSemanalDAO;
import com.TTPS2024.buffet.dao.carta.producto.ComidaDAO;
import com.TTPS2024.buffet.dao.carta.producto.MenuDAO;
import com.TTPS2024.buffet.dao.sugerencia.SugerenciaDAO;
import com.TTPS2024.buffet.dao.usuario.AlumnoDAO;

import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.model.carta.CartaSemanal;
import com.TTPS2024.buffet.model.carta.DiaSemana;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.TipoComida;
import com.TTPS2024.buffet.model.sugerencia.TipoSugerencia;
import com.TTPS2024.buffet.service.carrito.CompraService;
import com.TTPS2024.buffet.service.carta.CartaDelDiaService;
import com.TTPS2024.buffet.service.carta.CartaSemanalService;
import com.TTPS2024.buffet.service.carta.producto.ComidaService;
import com.TTPS2024.buffet.service.carta.producto.MenuService;
import com.TTPS2024.buffet.service.sugerencia.SugerenciaService;
import com.TTPS2024.buffet.service.usuario.AlumnoService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractGenericTest {
    @Autowired
    protected MenuDAO menuDAO;
    @Autowired
    protected MenuService menuService;
    @Autowired
    protected ComidaDAO comidaDAO;
    @Autowired
    protected ComidaService comidaService;
    @Autowired
    protected AlumnoService alumnoService;
    @Autowired
    protected AlumnoDAO alumnoDAO;
    @Autowired
    protected CompraDAO compraDAO;
    @Autowired
    protected CompraService compraService;
    @Autowired
    protected SugerenciaDAO sugerenciaDAO;
    @Autowired
    protected SugerenciaService sugerenciaService;
    @Autowired
    protected CartaDelDiaDAO cartaDelDiaDAO;
    @Autowired
    protected CartaDelDiaService cartaDelDiaService;
    @Autowired
    protected CartaSemanalDAO cartaSemanalDAO;
    @Autowired
    protected CartaSemanalService cartaSemanalService;

    @BeforeAll
    public void setUp(){

        this.createAlumnoRequestWithData().forEach(request -> {
            this.alumnoService.save(request);
        });

        this.createComidaRequestWithData().forEach(request -> {
            this.comidaService.save(request);
        });

        this.createMenuRequestWithData().forEach(request -> {
            this.menuService.save(request);
        });
        this.createCartaDelDiaRequestWithData().forEach(request -> {
            this.cartaDelDiaService.save(request);
        });

        this.createCartaSemanalRequestWithData().forEach(request -> {
            this.cartaSemanalService.save(request);
        });

    }


    @AfterAll
    public void deleteAllTest() {
        this.cartaDelDiaService.getAll().forEach(cartaDelDia ->
                this.cartaDelDiaService.delete(cartaDelDia.getId()));

        this.cartaSemanalService.getAll().forEach(cartaSemanal ->
                this.cartaSemanalService.delete(cartaSemanal.getId()));

        this.sugerenciaService.getAll().forEach(sugerencia ->
                this.sugerenciaService.delete(sugerencia.getId()));

        this.compraService.getAll().forEach(compra ->
            this.compraService.delete(compra.getId()));

        this.comidaService.getAll().forEach(comida ->
            this.comidaService.delete(comida.getId()));

        this.menuService.getAll().forEach(menu ->
            this.menuService.delete(menu.getId()));

        this.alumnoService.getAll().forEach(alumno ->
            this.alumnoService.delete(alumno.getId()));

    }




    protected AlumnoRequest createAlumnoRequest(String nombre, String apellido, String email, Integer dni) {
        AlumnoRequest alumnoRequest = new AlumnoRequest();
        alumnoRequest.setNombre(nombre);
        alumnoRequest.setApellido(apellido);
        alumnoRequest.setEmail(email);
        alumnoRequest.setDni(dni);
        alumnoRequest.setFoto(null);
        return alumnoRequest;
    }

    private List<MenuRequest> createMenuRequestWithData() {
        return List.of(
                this.createMenuRequest("Menu Lunes Comun", 4500.0, List.of(
                        this.comidaService.getProductsByName("Milanesa").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Helado").get(0)), false
                ),
                this.createMenuRequest("Menu Lunes Vegano", 3500.0, List.of(
                        this.comidaService.getProductsByName("Pasta").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Manzana").get(0)), true
                ),
                this.createMenuRequest("Menu Martes Comun", 4500.0, List.of(
                        this.comidaService.getProductsByName("Milanesa").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Helado").get(0)), false
                ),
                this.createMenuRequest("Menu Martes Vegano", 3500.0, List.of(
                        this.comidaService.getProductsByName("Pasta").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Manzana").get(0)), true
                ),
                this.createMenuRequest("Menu Miercoles Comun", 4500.0, List.of(
                        this.comidaService.getProductsByName("Milanesa").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Helado").get(0)), false
                ),
                this.createMenuRequest("Menu Miercoles Vegano", 3500.0, List.of(
                        this.comidaService.getProductsByName("Pasta").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Manzana").get(0)), true
                ),
                this.createMenuRequest("Menu Jueves Comun", 4500.0, List.of(
                        this.comidaService.getProductsByName("Milanesa").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Helado").get(0)), false
                ),
                this.createMenuRequest("Menu Jueves Vegano", 3500.0, List.of(
                        this.comidaService.getProductsByName("Pasta").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Manzana").get(0)), true
                ),
                this.createMenuRequest("Menu Viernes Comun", 4500.0, List.of(
                        this.comidaService.getProductsByName("Milanesa").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Helado").get(0)), false
                ),
                this.createMenuRequest("Menu Viernes Vegano", 3500.0, List.of(
                        this.comidaService.getProductsByName("Pasta").get(0),
                        this.comidaService.getProductsByName("Ensalada").get(0),
                        this.comidaService.getProductsByName("Manzana").get(0)), true
                )
        );
    }
    protected MenuRequest createMenuRequest(String nombre, Double precio, List<Comida> comidasList, boolean isVeggie) {
        MenuRequest menuRequest = new MenuRequest();
        menuRequest.setNombre(nombre);
        menuRequest.setPrecio(precio);
        menuRequest.setImagen(null);
        menuRequest.setComidas(comidasList);
        menuRequest.setVeggie(isVeggie);
        return menuRequest;
    }

    private List<AlumnoRequest> createAlumnoRequestWithData() {
        return List.of(
                this.createAlumnoRequest("Chicho","Siesta","chicho@gmail.com",1111111),
                this.createAlumnoRequest("Roman","Riquelme","roman@gmail.com",22222222),
                this.createAlumnoRequest("Enzo","Perez","enzo@gmail.com",33333333)
        );
    }


    private List<ComidaRequest> createComidaRequestWithData() {
        return List.of(
                this.createComidaRequest("Milanesa", 2500.0, TipoComida.PLATO_PRINCIPAL),
                this.createComidaRequest("Ensalada", 1500.0, TipoComida.ENTRADA),
                this.createComidaRequest("Pasta", 2500.0, TipoComida.PLATO_PRINCIPAL),
                this.createComidaRequest("Manzana", 1000.0, TipoComida.POSTRE),
                this.createComidaRequest("Helado", 1000.0, TipoComida.POSTRE)
        );
    }

    protected ComidaRequest createComidaRequest(String nombre, Double precio, TipoComida tipoComida) {
        ComidaRequest comidaRequest = new ComidaRequest();
        comidaRequest.setNombre(nombre);
        comidaRequest.setPrecio(precio);
        comidaRequest.setTipoComida(tipoComida);
        return comidaRequest;
    }

    protected SugerenciaRequest createSugerenciaRequest(String titulo, TipoSugerencia tipoSugerencia, String mensajeOriginal, int alumnoDni) {
        SugerenciaRequest sugerenciaRequest = new SugerenciaRequest();
        sugerenciaRequest.setTitulo(titulo);
        sugerenciaRequest.setTipoSugerencia(tipoSugerencia);
        sugerenciaRequest.setMensajeOriginal(mensajeOriginal);
        sugerenciaRequest.setAlumnoId(this.alumnoService.getUserByDni(alumnoDni).getId());
        return sugerenciaRequest;
    }

    protected CartaDelDiaRequest createCartaDelDiaRequest (String menuComunP, String menuVegetarianoP, DiaSemana diaSemana) {
        CartaDelDiaRequest cartaDelDiaRequest = new CartaDelDiaRequest();

        cartaDelDiaRequest.setMenues(List.of(this.menuService.getProductsByName(menuComunP).get(0),this.menuService.getProductsByName(menuVegetarianoP).get(0)));
        cartaDelDiaRequest.setDiaSemana(diaSemana);
        cartaDelDiaRequest.setCartaSemanal(null);

        return cartaDelDiaRequest;
    }

    protected List<CartaDelDiaRequest> createCartaDelDiaRequestWithData() {

        return List.of(
                this.createCartaDelDiaRequest("Menu Lunes Comun", "Menu Lunes Vegano", DiaSemana.LUNES),
                this.createCartaDelDiaRequest("Menu Martes Comun", "Menu Martes Vegano", DiaSemana.MARTES),
                this.createCartaDelDiaRequest("Menu Miercoles Comun", "Menu Miercoles Vegano", DiaSemana.MIERCOLES),
                this.createCartaDelDiaRequest("Menu Jueves Comun", "Menu Jueves Vegano", DiaSemana.JUEVES),
                this.createCartaDelDiaRequest("Menu Viernes Comun", "Menu Viernes Vegano", DiaSemana.VIERNES)
        );

    }
    protected List<CartaSemanalRequest> createCartaSemanalRequestWithData() {
        return List.of(
                this.createCartaSemanalRequest("Carta de Navidad",List.of(
                                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.LUNES).get(0),
                                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.MARTES).get(0),
                                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.MIERCOLES).get(0),
                                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.JUEVES).get(0),
                                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.VIERNES).get(0)
                        ))
                );
    }

    protected CartaSemanalRequest createCartaSemanalRequest(String nombre, List<CartaDelDia> cartasDelDia) {
        CartaSemanalRequest cartaSemanalRequest = new CartaSemanalRequest();
        cartaSemanalRequest.setNombre(nombre);
        cartaSemanalRequest.setCartasDelDia(cartasDelDia);
        return cartaSemanalRequest;
    }


}
