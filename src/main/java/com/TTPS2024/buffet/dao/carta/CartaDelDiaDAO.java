package com.TTPS2024.buffet.dao.carta;

import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.model.carta.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaDelDiaDAO extends JpaRepository<CartaDelDia, Long> {
    List<CartaDelDia> getCartaDelDiaByDiaSemana(DiaSemana diaSemana);
}
