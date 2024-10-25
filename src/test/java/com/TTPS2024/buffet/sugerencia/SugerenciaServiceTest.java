package com.TTPS2024.buffet.sugerencia;


import com.TTPS2024.buffet.AbstractGenericTest;
import com.TTPS2024.buffet.controller.request.sugerencia.SugerenciaRequest;
import com.TTPS2024.buffet.model.sugerencia.Sugerencia;
import com.TTPS2024.buffet.model.sugerencia.TipoSugerencia;
import com.TTPS2024.buffet.model.usuario.Alumno;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SugerenciaServiceTest extends AbstractGenericTest {

    @ParameterizedTest
    @MethodSource("createSugerenciaRequestWithData")
    @Order(2)
    public void testCreateSugerencia(SugerenciaRequest sugerenciaRequest) {
        Sugerencia sugerencia = this.sugerenciaService.save(sugerenciaRequest);
        this.testQueryAndValidateSugerenciaById(sugerencia.getId(), sugerenciaRequest);
    }

    private void testQueryAndValidateSugerenciaById(Long id, SugerenciaRequest sugerenciaRequest) {
        Sugerencia sugerencia = this.sugerenciaService.getById(id);
        assertNotNull(sugerencia);
        assertEquals(sugerenciaRequest.getTitulo(), sugerencia.getTitulo());
        assertEquals(sugerenciaRequest.getTipoSugerencia(), sugerencia.getTipo());
        assertEquals(sugerenciaRequest.getMensajeOriginal(), sugerencia.getMensajeOriginal());
        assertEquals(sugerenciaRequest.getAlumnoId(), sugerencia.getUsuario().getId());
    }

    @Test
    @Order(3)
    public void getAllSugerenciasTest(){
        List<Sugerencia> sugerencias = this.sugerenciaService.getAll();
        assertNotNull(sugerencias);
        assertEquals(3, sugerencias.size());
    }

    @Test
    @Order(4)
    public void getSugerenciaByTipoTest(){
        List<Sugerencia> sugerencias = this.sugerenciaService.getSugerenciaByTipo(TipoSugerencia.ALIMENTOS);
        assertNotNull(sugerencias);
        assertEquals(2, sugerencias.size());

    }

    @AfterAll
    public void deleteAllTest(){
        List<Sugerencia> sugerencias = this.sugerenciaService.getAll();
        sugerencias.forEach(sugerencia -> this.sugerenciaService.delete(sugerencia.getId()));
        sugerencias = this.sugerenciaService.getAll();
        assertNotNull(sugerencias);
        assertEquals(0,sugerencias.size());
        List<Alumno> alumnos = this.alumnoService.getAll();
        alumnos.forEach(alumno -> this.alumnoService.delete(alumno.getId()));
        alumnos = this.alumnoService.getAll();
        assertEquals(0,alumnos.size());
    }

    private Stream<Arguments> createSugerenciaRequestWithData() {
        return Stream.of(
                Arguments.of(this.createSugerenciaRequest("titulo1", TipoSugerencia.ALIMENTOS, "mensaje1", 1111111)),
                Arguments.of(this.createSugerenciaRequest("titulo2", TipoSugerencia.ALIMENTOS, "mensaje2", 22222222)),
                Arguments.of(this.createSugerenciaRequest("titulo3", TipoSugerencia.INFRAESTRUCTURA, "mensaje3", 33333333))
        );
    }




}
