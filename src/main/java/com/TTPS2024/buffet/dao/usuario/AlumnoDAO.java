package com.TTPS2024.buffet.dao.usuario;

import com.TTPS2024.buffet.model.usuario.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoDAO extends JpaRepository<Alumno,Long>, UsuarioDAO {
    Alumno findByDni(Integer dni);
    Alumno findByEmail(String email);
}
