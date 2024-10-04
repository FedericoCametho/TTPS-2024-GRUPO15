package com.TTPS2024.buffet.dao.usuario;

import com.TTPS2024.buffet.model.usuario.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorDAO extends JpaRepository<Administrador,Long>, UsuarioDAO {
    Administrador findByDni(Integer dni);
    Administrador findByEmail(String email);
}
