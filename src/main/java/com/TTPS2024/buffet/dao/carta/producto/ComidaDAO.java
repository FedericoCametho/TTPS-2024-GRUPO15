package com.TTPS2024.buffet.dao.carta.producto;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.TipoComida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ComidaDAO extends ProductoComercializableDAO<Comida>, JpaRepository<Comida, Long> {
    List<Comida> getComidasByTipoComida(TipoComida tipoComida);
}
