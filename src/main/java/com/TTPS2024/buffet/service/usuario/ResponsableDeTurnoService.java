package com.TTPS2024.buffet.service.usuario;



import com.TTPS2024.buffet.controller.request.usuario.ResponsableDeTurnoRequest;
import com.TTPS2024.buffet.dao.usuario.ResponsableDeTurnoDAO;
import com.TTPS2024.buffet.model.usuario.ResponsableDeTurno;
import com.TTPS2024.buffet.model.usuario.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
@Service
public class ResponsableDeTurnoService extends UsuarioService<ResponsableDeTurno,ResponsableDeTurnoDAO, ResponsableDeTurnoRequest> {
    private static final Logger LOGGER = Logger.getLogger(ResponsableDeTurnoService.class.getName());
    @Autowired
    public ResponsableDeTurnoService(ResponsableDeTurnoDAO responsableDeTurnoDAO) {
        super(responsableDeTurnoDAO);
    }

    public List<ResponsableDeTurno> getResponsablesDeTurnoByTurno(Turno turno) {
        return this.dao.getByTurno(turno);
    }

    @Override
    protected void sanitizeRequestSpecificFields(ResponsableDeTurnoRequest usuarioRequest) {
        if(usuarioRequest.getTurno() == null){
            LOGGER.info("ERROR EN EL REQUEST: El turno es requerido");
            throw new IllegalArgumentException("El turno es requerido y solo pueden ser MANANA o TARDE");
        }
    }

    @Override
    protected void setUpdateSpecificFields(ResponsableDeTurno responsableDeTurno, ResponsableDeTurnoRequest responsableDeTurnoRequest) {
        responsableDeTurno.setTurno(responsableDeTurnoRequest.getTurno());
    }
    @Override
    protected ResponsableDeTurno createUsuario(ResponsableDeTurnoRequest usuarioRequest) {
        return new ResponsableDeTurno(usuarioRequest.getDni(), usuarioRequest.getEmail(),usuarioRequest.getNombre(), usuarioRequest.getApellido(), usuarioRequest.getTurno());
    }






}
