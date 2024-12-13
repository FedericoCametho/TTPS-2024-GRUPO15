package com.TTPS2024.buffet.service.usuario;



import com.TTPS2024.buffet.controller.request.usuario.create.AlumnoRequest;
import com.TTPS2024.buffet.controller.request.usuario.update.AlumnoRequestUpdate;
import com.TTPS2024.buffet.dao.usuario.AlumnoDAO;
import com.TTPS2024.buffet.helper.RequestValidatorHelper;
import com.TTPS2024.buffet.model.usuario.Alumno;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AlumnoService extends UsuarioService<Alumno, AlumnoDAO, AlumnoRequest, AlumnoRequestUpdate>{

    private static final Logger LOGGER = Logger.getLogger(AlumnoService.class.getName());
    @Autowired
    public AlumnoService(AlumnoDAO alumnoDAO) {
        super(alumnoDAO);
    }

    public List<Alumno> getAlumnosByEnabled() {
        return this.dao.getByHabilitado();
    }

    @Override
    protected Alumno createUsuario(AlumnoRequest alumnoRequest) {
        Alumno alumno =  new Alumno(alumnoRequest.getDni(), alumnoRequest.getEmail(),alumnoRequest.getNombre(), alumnoRequest.getApellido(), alumnoRequest.getContrasena());
        this.setUpdateSpecificFields(alumno, alumnoRequest);
        return alumno;
    }

    @Override
    protected void setUpdateSpecificFields(Alumno alumno, AlumnoRequest alumnoRequest) {
        if(alumnoRequest.getFoto() != null){
            String base64String = alumnoRequest.getFoto();
            if (base64String.startsWith("data:image")) {
                base64String = base64String.substring(base64String.indexOf(",") + 1);
            }
            alumno.setFotoDePerfil(Base64.getDecoder().decode(base64String));
        }
        this.validarHabilitado(alumno, alumnoRequest.isHabilitado());
    }

    @Override
    protected void sanitizeRequestSpecificFields(AlumnoRequest usuarioRequest) {
        // No se requiere sanitizar campos específicos
    }

    @Override
    protected void setUpdateSpecificFields(Alumno user, AlumnoRequestUpdate usuarioRequest) {
        user.setFotoDePerfil(usuarioRequest.getFoto());
        this.validarHabilitado(user, usuarioRequest.isHabilitado());
    }

    @Override
    protected void sanitizeRequestSpecificFields(AlumnoRequestUpdate usuarioRequest) {

    }

    private void validarHabilitado(Alumno alumno, boolean nuevoEstado){
        if(nuevoEstado){
            alumno.habilitar();
        } else {
            alumno.deshabilitar();
        }
    }

}
