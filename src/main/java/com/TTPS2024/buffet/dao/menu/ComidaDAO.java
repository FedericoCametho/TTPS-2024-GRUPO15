package com.TTPS2024.buffet.dao.menu;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComidaDAO extends JpaRepository<Comida, Long> , ProductoComercializableDAO<Comida>{
    @Query("SELECT c FROM Comida c WHERE c.nombre = :nombre")
    List<Comida> findByNombre(@Param("nombre") String nombre);
    @Query("SELECT c FROM Comida c WHERE c.nombre = :precio")
    List<Comida> findByPrecio(@Param("precio")Double precio);
}
