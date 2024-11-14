package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.request.carta.producto.ComidaRequest;
import com.TTPS2024.buffet.model.carta.producto.Comida;
import com.TTPS2024.buffet.service.carta.producto.ComidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/agregar")
    public ResponseEntity<Comida> save(@RequestBody ComidaRequest request) {
        return new ResponseEntity<>(comidaService.save(request), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Comida> update(@PathVariable("id") Long id, @RequestBody ComidaRequest request) {
        Comida comida = comidaService.update(id, request);
        return (comida != null) ? new ResponseEntity<>(comida, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Comida>> getComidas() {
        return new ResponseEntity<>(comidaService.getAll(),HttpStatus.OK);
    }
    @GetMapping("listar/{id}")
    public ResponseEntity<Comida> getComida(@PathVariable("id") Long id) {
         return new ResponseEntity<>(this.comidaService.getProductById(id), HttpStatus.OK);
    }
    @GetMapping("/listarPorNombre")
    public ResponseEntity<List<Comida>> getComidasByName(@RequestParam String nombre) {
        return new ResponseEntity<>(comidaService.getProductsByName(nombre), HttpStatus.OK);
    }

}
