package com.TTPS2024.buffet.helper.transformer.usuario;

import com.TTPS2024.buffet.controller.dto.usuario.AlumnoDTO;
import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.sugerencia.Sugerencia;
import com.TTPS2024.buffet.model.usuario.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoTransformer {

    public static AlumnoDTO toDTO(Alumno alumno){
        AlumnoDTO dto = new AlumnoDTO();
        dto.setId(alumno.getId());
        dto.setEmail(alumno.getEmail());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        dto.setDni(alumno.getDni());
        dto.setRol(alumno.getRol());
        dto.setHabilitado(alumno.isHabilitado());
        dto.setFotoDePerfil(alumno.getFotoDePerfil());
        dto.setCompras(alumno.getCompras().stream().mapToLong(Compra::getId).boxed().toList());
        dto.setSugerencias(alumno.getSugerencias().stream().mapToLong(Sugerencia::getId).boxed().toList());
        return dto;
    }

    public static List<AlumnoDTO> toDTOList(List<Alumno> alumnos) {
        return (alumnos.isEmpty()) ? new ArrayList<>() :
                alumnos.stream().map(AlumnoTransformer::toDTO).toList();
    }
}
