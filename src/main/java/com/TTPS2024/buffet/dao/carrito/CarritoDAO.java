package com.TTPS2024.buffet.dao.carrito;

import com.TTPS2024.buffet.model.carrito.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoDAO extends JpaRepository<Carrito, Long> {
}
