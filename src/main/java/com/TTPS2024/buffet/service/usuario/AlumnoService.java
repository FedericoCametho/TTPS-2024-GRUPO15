package com.TTPS2024.buffet.service.usuario;

import com.TTPS2024.buffet.dao.usuario.AlumnoDAO;
import com.TTPS2024.buffet.model.permiso.Rol;
import com.TTPS2024.buffet.model.request.usuario.AlumnoRequest;
import com.TTPS2024.buffet.model.usuario.Alumno;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {

    private AlumnoDAO alumnoDAO;

    @Autowired
    public AlumnoService(AlumnoDAO alumnoDAO){
        this.alumnoDAO = alumnoDAO;
    }

    public Alumno getAlumnoById(Long id){
        return alumnoDAO.findById(id).orElse(null);
    }

    public List<Alumno> getAlumnos(){
        return alumnoDAO.findAll();
    }

    @Transactional
    public Alumno saveAlumno(AlumnoRequest alumnoRequest){
        this.sanitizeAlumnoRequest(alumnoRequest);
        Alumno alumno = new Alumno(alumnoRequest.getDni(), alumnoRequest.getEmail(), alumnoRequest.getNombre(), alumnoRequest.getApellido(), Rol.ALUMNO);
        return alumnoDAO.save(alumno);
    }

    private void sanitizeAlumnoRequest(AlumnoRequest alumnoRequest){
        if(alumnoRequest.getDni() == null || alumnoRequest.getDni() < 0){
            throw new IllegalArgumentException("DNI de alumno no puede ser nulo o negativo");
        }
        if(alumnoRequest.getEmail() == null || alumnoRequest.getEmail().isEmpty()){
            throw new IllegalArgumentException("Email de alumno no puede ser nulo o vacio");
        }
        if(alumnoRequest.getNombre() == null || alumnoRequest.getNombre().isEmpty()){
            throw new IllegalArgumentException("Nombre de alumno no puede ser nulo o vacio");
        }
        if(alumnoRequest.getApellido() == null || alumnoRequest.getApellido().isEmpty()){
            throw new IllegalArgumentException("Apellido de alumno no puede ser nulo o vacio");
        }
    }

}
