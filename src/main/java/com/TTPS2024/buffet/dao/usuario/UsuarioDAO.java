package com.TTPS2024.buffet.dao.usuario;

import com.TTPS2024.buffet.model.permiso.Rol;
import com.TTPS2024.buffet.model.usuario.Usuario;


import java.util.List;
public interface UsuarioDAO<T extends Usuario> {
    T findByEmail(String email);
    T findByDni(Integer dni);
    List<T> getUsuariosByRol(Rol rol);
    List<T> getUsuariosByNombreContaining(String nombre);
    List<T> getUsuariosByApellidoContaining(String apellido);

}
