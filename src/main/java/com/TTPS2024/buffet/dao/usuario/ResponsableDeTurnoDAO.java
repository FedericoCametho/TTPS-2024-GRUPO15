package com.TTPS2024.buffet.dao.usuario;

import com.TTPS2024.buffet.model.usuario.ResponsableDeTurno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsableDeTurnoDAO extends JpaRepository<ResponsableDeTurno,Long>, UsuarioDAO {
    ResponsableDeTurno findByDni(Integer dni);
    ResponsableDeTurno findByEmail(String email);
}
