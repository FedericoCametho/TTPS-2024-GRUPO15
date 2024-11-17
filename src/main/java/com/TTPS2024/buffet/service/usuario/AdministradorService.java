package com.TTPS2024.buffet.service.usuario;


import com.TTPS2024.buffet.controller.request.usuario.create.AdministradorRequest;
import com.TTPS2024.buffet.controller.request.usuario.update.AdministradorRequestUpdate;
import com.TTPS2024.buffet.dao.usuario.AdministradorDAO;
import com.TTPS2024.buffet.model.usuario.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService extends UsuarioService<Administrador, AdministradorDAO, AdministradorRequest, AdministradorRequestUpdate> {
    @Autowired
    public AdministradorService(AdministradorDAO administradorDAO) {
        super(administradorDAO);
    }

    @Override
    protected Administrador createUsuario(AdministradorRequest usuarioRequest) {
        return new Administrador(usuarioRequest.getDni(), usuarioRequest.getEmail(),usuarioRequest.getNombre(), usuarioRequest.getApellido(), usuarioRequest.getContrasena());
    }

    @Override
    protected void setUpdateSpecificFields(Administrador user, AdministradorRequest usuarioRequest) {
    }

    @Override
    protected void sanitizeRequestSpecificFields(AdministradorRequest usuarioRequest) {
    }

    @Override
    protected void setUpdateSpecificFields(Administrador user, AdministradorRequestUpdate usuarioRequest) {
    }

    @Override
    protected void sanitizeRequestSpecificFields(AdministradorRequestUpdate usuarioRequest) {

    }
}
