package com.TTPS2024.buffet.model.usuario;


import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.permiso.Rol;
import com.TTPS2024.buffet.model.sugerencia.Sugerencia;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Alumno extends Usuario {
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Compra> compras;
    private Boolean habilitado;
    @Lob
    private byte[] fotoDePerfil;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Sugerencia> sugerencias;


    public Alumno(Integer dni, String email, String nombre, String apellido, String contrasena) {
        super(dni, email, nombre, apellido, Rol.ALUMNO, contrasena);
        this.compras = new ArrayList<>();
        this.sugerencias = new ArrayList<>();
        this.habilitado = true;
    }

    public Alumno() {

    }

    public List<Compra> getCompras() {
        return compras;
    }
    public void agregarCompra(Compra compra){
        this.compras.add(compra);
    }
    public void habilitar(){
        this.habilitado = true;
    }
    public void deshabilitar(){
        this.habilitado = false;
    }
    public Boolean isHabilitado(){
        return this.habilitado;
    }
    public byte[] getFotoDePerfil() {
        return fotoDePerfil;
    }

    public void setFotoDePerfil(byte[] fotoDePerfil) {
        this.fotoDePerfil = fotoDePerfil;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Sugerencia> getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(List<Sugerencia> sugerencias) {
        this.sugerencias = sugerencias;
    }
}
