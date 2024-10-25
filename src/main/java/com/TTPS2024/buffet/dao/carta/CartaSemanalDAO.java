package com.TTPS2024.buffet.dao.carta;

import com.TTPS2024.buffet.model.carta.CartaSemanal;
import com.TTPS2024.buffet.model.carta.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartaSemanalDAO extends JpaRepository<CartaSemanal, Long> {
    CartaSemanal findByNombre(String nombre);
}
