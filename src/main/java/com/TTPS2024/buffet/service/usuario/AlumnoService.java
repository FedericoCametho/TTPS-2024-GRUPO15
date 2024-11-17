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

    @Transactional
    public Alumno update(Long id, AlumnoRequestUpdate alumnoRequestUpdate){
        RequestValidatorHelper.validateID(id);
        this.sanitizeRequest(alumnoRequestUpdate);
        Alumno user;
        try{
            user = this.dao.getById(id);
        } catch (NoResultException e){
            LOGGER.info("El usuario no existe con el id: " + id);
            throw new IllegalArgumentException("El usuario no existe con el id: " + id);
        }
        user.setNombre(alumnoRequestUpdate.getNombre());
        user.setApellido(alumnoRequestUpdate.getApellido());
        user.setEmail(alumnoRequestUpdate.getEmail());
        user.setDni(alumnoRequestUpdate.getDni());
        this.setUpdateSpecificFields(user, alumnoRequestUpdate);
        return this.dao.saveAndFlush(user);
    }


    @Override
    protected Alumno createUsuario(AlumnoRequest alumnoRequest) {
        return new Alumno(alumnoRequest.getDni(), alumnoRequest.getEmail(),alumnoRequest.getNombre(), alumnoRequest.getApellido(), alumnoRequest.getContrasena());
    }

    @Override
    protected void setUpdateSpecificFields(Alumno alumno, AlumnoRequest alumnoRequest) {
        alumno.setFotoDePerfil(alumnoRequest.getFoto());
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
