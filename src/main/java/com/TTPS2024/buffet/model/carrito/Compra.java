package com.TTPS2024.buffet.model.carrito;

import com.TTPS2024.buffet.model.menu.Menu;
import com.TTPS2024.buffet.model.usuario.Alumno;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno usuario;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> menues;
    public Double importe;

    public Compra() {

    }
    public Compra(Carrito carrito){
        this.fecha = LocalDateTime.now();
        this.usuario = carrito.getUsuario();
        this.menues = carrito.getMenues();
        this.importe = carrito.getPrecioTotal();
    }


}
