package com.TTPS2024.buffet.dao.menu;

import com.TTPS2024.buffet.model.carta.producto.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDAO extends JpaRepository<Menu, Long>, ProductoComercializableDAO {
    @Query("SELECT m FROM Menu m WHERE m.nombre = :nombre")
    List<Menu> findByNombre(@Param("nombre") String nombre);
    @Query("SELECT m FROM Menu m WHERE m.precio = :precio")
    List<Menu> findByPrecio(@Param("precio") Double precio);
}
