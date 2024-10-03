package com.TTPS2024.buffet.dao.menu;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComidaDAO extends JpaRepository<Comida, Long> {
}
