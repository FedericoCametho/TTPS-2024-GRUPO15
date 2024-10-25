package com.TTPS2024.buffet.dao.usuario;

import com.TTPS2024.buffet.model.usuario.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministradorDAO extends UsuarioDAO<Administrador>,  JpaRepository<Administrador, Long> {

}
