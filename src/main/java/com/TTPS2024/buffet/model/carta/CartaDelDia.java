package com.TTPS2024.buffet.model.carta;

import com.TTPS2024.buffet.model.carta.producto.Menu;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class CartaDelDia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "menu_carta_del_dia",
            joinColumns = @JoinColumn(name = "carta_del_dia_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menues;
    private DiaSemana diaSemana;
    private boolean activa;
    @ManyToOne
    @JoinColumn(name = "carta_semanal_id")
    private CartaSemanal cartaSemanal;

    public CartaDelDia(List<Menu> menues, DiaSemana diaSemana) {
        this.menues = (menues != null && menues.size() == 2) ? menues : new ArrayList<>();
        this.diaSemana = diaSemana;
        this.activa = true;
    }

    public CartaDelDia() {
        this.menues = new ArrayList<>();
        this.activa = true;
    }

    public Long getId() {
        return id;
    }
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public List<Menu> getMenues() {
        return menues;
    }

    public void agregarMenuVeggie(Menu menu){
        if(this.menues.size() < 2 && this.menues.stream().noneMatch(Menu::isVeggie)){
            this.menues.add(menu);
        } else {
            Menu menuVeggie = this.menues.stream().filter(Menu::isVeggie).findFirst().get();
            this.menues.remove(menuVeggie);
            this.menues.add(menu);
        }
    }
    public void agregarMenuComun(Menu menu){
        if(this.menues.size() < 2 && this.menues.stream().noneMatch(men -> !men.isVeggie())){
            this.menues.add(menu);
        }else {
            Menu menuComun = this.menues.stream().filter(men -> !men.isVeggie()).findFirst().get();
            this.menues.remove(menuComun);
            this.menues.add(menu);
        }
    }

    public Menu getMenuVegetariano() {
        return menues.stream().filter(Menu::isVeggie).findFirst().orElse(null);
    }
    public Menu getMenuComun() {
        return menues.stream().filter(menu -> !menu.isVeggie()).findFirst().orElse(null);
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public CartaSemanal getCartaSemanal() {
        return cartaSemanal;
    }

    public void setCartaSemanal(CartaSemanal cartaSemanal) {
        this.cartaSemanal = cartaSemanal;
    }
}
