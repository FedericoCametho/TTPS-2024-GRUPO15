package com.TTPS2024.buffet.model.carta;

import com.TTPS2024.buffet.model.carta.producto.Menu;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Carta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Menu menuVegetariano;
    @OneToOne
    private Menu menuComun;
    private DiaSemana diaSemana;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activa;

    public Carta(Menu menuVegetariano, Menu menuComun, DiaSemana diaSemana, LocalDate fechaInicio, LocalDate fechaFin) {
        this.menuVegetariano = menuVegetariano;
        this.menuComun = menuComun;
        this.diaSemana = diaSemana;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activa = true;
    }

    public Carta() {
    }



    public Long getId() {
        return id;
    }
}
