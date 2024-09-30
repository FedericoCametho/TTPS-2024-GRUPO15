package com.TTPS2024.buffet.service.carrito;

import com.TTPS2024.buffet.dao.carrito.CarritoDAO;
import com.TTPS2024.buffet.model.carrito.Carrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {

    private CarritoDAO carritoDAO;
    @Autowired
    public CarritoService(CarritoDAO carritoDAO){
        this.carritoDAO = carritoDAO;
    }

    public List<Carrito> getCarritos(){
        return carritoDAO.findAll();
    }

}
