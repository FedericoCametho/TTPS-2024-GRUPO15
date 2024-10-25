package com.TTPS2024.buffet.dao.usuario;

import com.TTPS2024.buffet.model.usuario.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlumnoDAO extends UsuarioDAO<Alumno>, JpaRepository<Alumno, Long> {
    @Query("SELECT a FROM Alumno a WHERE a.habilitado = true")
    List<Alumno> getByHabilitado();

}
