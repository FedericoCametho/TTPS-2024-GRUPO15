package com.TTPS2024.buffet.helper.transformer.usuario;

import com.TTPS2024.buffet.controller.dto.usuario.ResponsableDeTurnoDTO;
import com.TTPS2024.buffet.model.usuario.ResponsableDeTurno;

import java.util.ArrayList;
import java.util.List;

public class ResponsableDeTurnoTransformer {
    public static ResponsableDeTurnoDTO toDTO(ResponsableDeTurno responsableDeTurno){
        ResponsableDeTurnoDTO dto = new ResponsableDeTurnoDTO();
        dto.setId(responsableDeTurno.getId());
        dto.setEmail(responsableDeTurno.getEmail());
        dto.setNombre(responsableDeTurno.getNombre());
        dto.setApellido(responsableDeTurno.getApellido());
        dto.setDni(responsableDeTurno.getDni());
        dto.setRol(responsableDeTurno.getRol());
        dto.setTurno(responsableDeTurno.getTurno());
        return dto;
    }

    public static List<ResponsableDeTurnoDTO> toDTOList(List<ResponsableDeTurno> responsablesDeTurno) {
        return (responsablesDeTurno.isEmpty()) ? new ArrayList<>() :
                responsablesDeTurno.stream().map(ResponsableDeTurnoTransformer::toDTO).toList();
    }
}
