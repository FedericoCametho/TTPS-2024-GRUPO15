package com.TTPS2024.buffet.dao.carta.producto;

import com.TTPS2024.buffet.model.carta.producto.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface MenuDAO extends ProductoComercializableDAO<Menu>, JpaRepository<Menu, Long> {

}
