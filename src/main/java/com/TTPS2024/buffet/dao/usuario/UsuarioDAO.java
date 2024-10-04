package com.TTPS2024.buffet.dao.usuario;

import com.TTPS2024.buffet.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO {
    Usuario findByEmail(String email);
    Usuario findByDni(Integer dni);
}
