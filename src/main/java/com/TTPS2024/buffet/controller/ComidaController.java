package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.request.ComidaRequest;
import com.TTPS2024.buffet.service.menu.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comida")
public class ComidaController {
    private ComidaService comidaService;
    @Autowired
    public ComidaController(ComidaService comidaService) {
        this.comidaService = comidaService;
    }

    @GetMapping("/listar")
    public List<Comida> getComidas() {
        return comidaService.getComidas();
    }

    @PostMapping("/agregar")
    public Comida saveComida(@RequestBody ComidaRequest request) {
        return comidaService.saveComida(request);
    }

}
