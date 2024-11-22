package com.TTPS2024.buffet.helper.transformer.sugerencia;

import com.TTPS2024.buffet.controller.dto.SugerenciaDTO;
import com.TTPS2024.buffet.model.sugerencia.Sugerencia;

import java.util.List;

public class SugerenciaTransformer {
    public static SugerenciaDTO toDTO(Sugerencia sugerencia) {
        SugerenciaDTO sugerenciaDTO = new SugerenciaDTO();
        sugerenciaDTO.setTitulo(sugerencia.getTitulo());
        sugerenciaDTO.setTipoSugerencia(sugerencia.getTipo());
        sugerenciaDTO.setMensajeOriginal(sugerencia.getMensajeOriginal());
        sugerenciaDTO.setAlumnoId(sugerencia.getUsuario().getId());
        return sugerenciaDTO;
    }

    public static List<SugerenciaDTO> toDTOList(List<Sugerencia> sugerencias) {
        return sugerencias.stream().map(SugerenciaTransformer::toDTO).toList();
    }
}
