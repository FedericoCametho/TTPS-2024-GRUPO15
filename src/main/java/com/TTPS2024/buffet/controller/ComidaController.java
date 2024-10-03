package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.model.carta.producto.EtiquetaComida;
import com.TTPS2024.buffet.model.carta.producto.TipoComida;
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
    public Comida saveComida(@RequestParam String nombre,@RequestParam TipoComida tipoComida,@RequestParam Double precio,@RequestParam(required = false) List<EtiquetaComida> etiquetas) {
        return comidaService.saveComida(nombre, tipoComida, precio, etiquetas);
    }

}
