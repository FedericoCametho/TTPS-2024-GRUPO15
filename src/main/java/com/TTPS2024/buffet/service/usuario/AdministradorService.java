package com.TTPS2024.buffet.service.usuario;


import com.TTPS2024.buffet.controller.request.usuario.AdministradorRequest;
import com.TTPS2024.buffet.dao.usuario.AdministradorDAO;
import com.TTPS2024.buffet.model.usuario.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService extends UsuarioService<Administrador, AdministradorDAO, AdministradorRequest> {
    @Autowired
    public AdministradorService(AdministradorDAO administradorDAO) {
        super(administradorDAO);
    }

    @Override
    protected Administrador createUsuario(AdministradorRequest usuarioRequest) {
        return new Administrador(usuarioRequest.getDni(), usuarioRequest.getEmail(),usuarioRequest.getNombre(), usuarioRequest.getApellido());
    }

    @Override
    protected void setUpdateSpecificFields(Administrador user, AdministradorRequest usuarioRequest) {
    }

    @Override
    protected void sanitizeRequestSpecificFields(AdministradorRequest usuarioRequest) {
    }

}