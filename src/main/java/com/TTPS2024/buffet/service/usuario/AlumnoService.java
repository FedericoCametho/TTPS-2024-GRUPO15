package com.TTPS2024.buffet.service.usuario;



import com.TTPS2024.buffet.controller.request.usuario.AlumnoRequest;
import com.TTPS2024.buffet.dao.usuario.AlumnoDAO;
import com.TTPS2024.buffet.model.usuario.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlumnoService extends UsuarioService<Alumno, AlumnoDAO, AlumnoRequest>{

    @Autowired
    public AlumnoService(AlumnoDAO alumnoDAO) {
        super(alumnoDAO);
    }

    public List<Alumno> getAlumnosByEnabled() {
        return this.dao.getByHabilitado();
    }
    @Override
    protected Alumno createUsuario(AlumnoRequest alumnoRequest) {
        return new Alumno(alumnoRequest.getDni(), alumnoRequest.getEmail(),alumnoRequest.getNombre(), alumnoRequest.getApellido());
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

    private void validarHabilitado(Alumno alumno, boolean nuevoEstado){
        if(nuevoEstado){
            alumno.habilitar();
        } else {
            alumno.deshabilitar();
        }
    }

}
