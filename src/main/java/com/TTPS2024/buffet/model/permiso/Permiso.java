package com.TTPS2024.buffet.model.permiso;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Permiso {
    /* USUARIOS_EDIT,
    USUARIOS_READ,
    USUARIOS_CREATE,
    USUARIOS_DELETE,
    USUARIOS_DESACTIVAR,
    MENU_EDIT,
    MENU_READ,
    MENU_CREATE,
    MENU_DELETE,
    COMIDA_EDIT,
    COMIDA_READ,
    COMIDA_CREATE,
    COMIDA_DELETE,
    SUGERENCIAS_DELETE,
    SUEGERENCIAS_CREATE,
    SUGERENCIAS_READ,
    SUGERENCIAS_REPLY
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "permisos")
    private List<Rol> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

}
