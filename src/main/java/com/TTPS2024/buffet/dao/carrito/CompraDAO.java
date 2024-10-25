package com.TTPS2024.buffet.dao.carrito;

import com.TTPS2024.buffet.model.carrito.Compra;
import com.TTPS2024.buffet.model.usuario.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompraDAO extends JpaRepository<Compra, Long> {
    @Query("SELECT c FROM Compra c WHERE c.usuario = :alumno")
    List<Compra> getComprasByAlumno(@Param("alumno") Alumno alumno);
    @Query("SELECT c FROM Compra c WHERE c.importe >= :precio")
    List<Compra> getByPrecioMayorQue(@Param("precio") Double precio);
    @Query("SELECT c FROM Compra c WHERE c.importe <= :precio")
    List<Compra> getByPrecioMenorQue(@Param("precio") Double precio);
}
