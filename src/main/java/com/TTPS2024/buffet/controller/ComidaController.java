package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.request.carta.producto.ComidaRequest;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.service.carta.producto.ComidaService;
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
        return comidaService.getAll();
    }

    @PostMapping("/agregar")
    public Comida saveComida(@RequestBody ComidaRequest request) {
        return comidaService.save(request);
    }

    @GetMapping("/listarPorNombre")
    public List<Comida> getComidasByName(@RequestParam String nombre) {
        return comidaService.getProductsByName(nombre);
    }

}
