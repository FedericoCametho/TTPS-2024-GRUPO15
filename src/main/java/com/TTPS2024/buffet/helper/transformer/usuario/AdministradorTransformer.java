package com.TTPS2024.buffet.helper.transformer.usuario;

import com.TTPS2024.buffet.controller.dto.usuario.AdministradorDTO;
import com.TTPS2024.buffet.model.usuario.Administrador;

import java.util.ArrayList;
import java.util.List;

public class AdministradorTransformer {
    public static AdministradorDTO toDTO(Administrador administrador){
        AdministradorDTO dto = new AdministradorDTO();
        dto.setId(administrador.getId());
        dto.setEmail(administrador.getEmail());
        dto.setNombre(administrador.getNombre());
        dto.setApellido(administrador.getApellido());
        dto.setDni(administrador.getDni());
        dto.setRol(administrador.getRol());
        return dto;
    }

    public static List<AdministradorDTO> toDTOList(List<Administrador> administradores) {
        return (administradores.isEmpty()) ? new ArrayList<>() :
                administradores.stream().map(AdministradorTransformer::toDTO).toList();
    }
}
