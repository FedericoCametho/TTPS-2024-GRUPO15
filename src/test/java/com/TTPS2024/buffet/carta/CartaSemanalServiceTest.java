package com.TTPS2024.buffet.carta;


import com.TTPS2024.buffet.AbstractGenericTest;
import com.TTPS2024.buffet.controller.request.carta.CartaSemanalRequest;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.model.carta.CartaSemanal;
import com.TTPS2024.buffet.model.carta.DiaSemana;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartaSemanalServiceTest extends AbstractGenericTest {





    @Test
    @Order(1)
    public void testCreateCartaSemanal() {
        CartaSemanalRequest cartaSemanalRequest = this.createCartaSemanalRequest("Carta de EXTRA",List.of(
                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.LUNES).get(0),
                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.MARTES).get(0),
                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.MIERCOLES).get(0),
                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.JUEVES).get(0),
                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.VIERNES).get(0)
        ));
        CartaSemanal cartaSemanal = this.cartaSemanalService.save(cartaSemanalRequest);
        this.testQueryAndValidateCartaSemanalById(cartaSemanal.getId(), cartaSemanalRequest);
    }



    private void testQueryAndValidateCartaSemanalById(Long id, CartaSemanalRequest cartaSemanalRequest) {
        CartaSemanal cartaSemanal = this.cartaSemanalService.getById(id);

        assertNotNull(cartaSemanal);
        assertEquals(5, cartaSemanal.getCartas().size());

        Set<Long> requestCartasDelDiaIds = cartaSemanalRequest.getCartasDelDia().stream().map(CartaDelDia::getId).collect(Collectors.toSet());
        Set<Long> cartasDelDiaIds = cartaSemanal.getCartas().stream().map(CartaDelDia::getId).collect(Collectors.toSet());
        assertEquals(requestCartasDelDiaIds, cartasDelDiaIds);
    }

    @Test
    @Order(2)
    public void testUpdateCartaSemanal() {
        CartaDelDia cartaDelDia = this.cartaDelDiaService.save(createCartaDelDiaRequest("Menu Miercoles Comun", "Menu Miercoles Vegano", DiaSemana.LUNES));
        CartaSemanal cartaSemanal = this.cartaSemanalService.getByNombre("Carta de EXTRA");
        CartaSemanalRequest cartaSemanalRequest = new CartaSemanalRequest();
        cartaSemanalRequest.setNombre(cartaSemanal.getNombre() + " UPDATED");
        cartaSemanalRequest.setCartasDelDia(List.of(
                this.cartaDelDiaService.getById(cartaDelDia.getId()),
                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.MARTES).get(0),
                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.MIERCOLES).get(0),
                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.JUEVES).get(0),
                this.cartaDelDiaService.getCartaDelDiaByDiaSemana(DiaSemana.VIERNES).get(0)
        ));


        CartaSemanal cartaSemanalUpdate = this.cartaSemanalService.update(cartaSemanal.getId(), cartaSemanalRequest);
        this.testQueryAndValidateCartaSemanalById(cartaSemanalUpdate.getId(), cartaSemanalRequest);
    }



}
