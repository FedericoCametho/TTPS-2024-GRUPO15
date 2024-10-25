package com.TTPS2024.buffet.dao.usuario;

import com.TTPS2024.buffet.model.usuario.ResponsableDeTurno;
import com.TTPS2024.buffet.model.usuario.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResponsableDeTurnoDAO extends UsuarioDAO<ResponsableDeTurno>, JpaRepository<ResponsableDeTurno, Long> {
    List<ResponsableDeTurno> getByTurno(Turno turno);

}
