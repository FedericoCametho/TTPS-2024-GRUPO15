package com.TTPS2024.buffet.model.carta.producto;

import com.TTPS2024.buffet.model.carrito.Carrito;
import com.TTPS2024.buffet.model.carrito.Compra;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Menu implements ProductoComercializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nombre;
    protected Double precio;
    @Lob
    private byte[] foto;
    @ManyToMany(mappedBy = "menues", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comida> comidas;

    @ManyToMany
    @JoinTable(
            name = "menu_carrito",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "carrito_id")
    )
    private List<Carrito> carritos;
    @ManyToMany(mappedBy = "menues", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compra> compras;

    public Menu(String titulo, List<Comida> comidas, Double precio) {
        this.nombre = titulo;
        this.precio = precio;
        this.comidas = comidas;
    }

    public Menu() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getId() {
        return this.id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public List<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(List<Comida> comidas) {
        this.comidas = comidas;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}
