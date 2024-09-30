package com.TTPS2024.buffet.service.carrito;

import com.TTPS2024.buffet.dao.carrito.CarritoDAOImpl;
import com.TTPS2024.buffet.model.carrito.Carrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {

    private CarritoDAOImpl carritoDAO;
    @Autowired
    public CarritoService(CarritoDAOImpl carritoDAO){
        this.carritoDAO = carritoDAO;
    }

    public List<Carrito> getCarritos(){
        return carritoDAO.findAll();
    }

}
