package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.model.carrito.Carrito;
import com.TTPS2024.buffet.service.carrito.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CarritoController {

    private CarritoService carritoService;

    @Autowired
    public CarritoController(CarritoService carritoService){
        this.carritoService = carritoService;
    }

    @GetMapping("/carrito")
    public List<Carrito> getCarritos(){
        return carritoService.getCarritos();
    }
}
