package com.TTPS2024.buffet.model.carrito;

import com.TTPS2024.buffet.model.menu.Menu;
import com.TTPS2024.buffet.model.usuario.Alumno;
import com.TTPS2024.buffet.model.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "carritos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> menues;
    @OneToOne
    private Alumno usuario;

    public Carrito() {
        this.menues = new ArrayList<>();
    }
    public void setUsuario(Alumno  alumno){
        this.usuario = alumno;
    }
    public Alumno getUsuario(){
        return this.usuario;
    }

    public boolean agregarMenu(Menu menu){
        return this.menues.add(menu);
    }

    public boolean quitarMenu(Menu menu){
        return this.menues.remove(menu);
    }
    public List<Menu> getMenues() {
        return menues;
    }

    public Double getPrecioTotal(){
        return this.menues.stream().mapToDouble(Menu::getPrecio).sum();
    }

}
