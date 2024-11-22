package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.dto.carta.producto.ComidaDTO;
import com.TTPS2024.buffet.controller.request.carta.producto.ComidaRequest;
import com.TTPS2024.buffet.helper.transformer.carta.producto.ComidaTransformer;
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
    public ResponseEntity<ComidaDTO> save(@RequestBody ComidaRequest request) {
        Comida comida = this.comidaService.save(request);
        return new ResponseEntity<>(ComidaTransformer.toDTO(comida), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ComidaDTO> update(@PathVariable("id") Long id, @RequestBody ComidaRequest request) {
        Comida comida = comidaService.update(id, request);
        return (comida != null) ? new ResponseEntity<>(ComidaTransformer.toDTO(comida), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<ComidaDTO>> getComidas() {
        List<Comida> comidas = this.comidaService.getAll();
        return new ResponseEntity<>(ComidaTransformer.toDTOList(comidas),HttpStatus.OK);
    }
    @GetMapping("listar/{id}")
    public ResponseEntity<ComidaDTO> getComida(@PathVariable("id") Long id) {
        Comida comida = this.comidaService.getProductById(id);
        return new ResponseEntity<>(ComidaTransformer.toDTO(comida), HttpStatus.OK);
    }
    @GetMapping("/listarPorNombre")
    public ResponseEntity<List<ComidaDTO>> getComidasByName(@RequestParam String nombre) {
        List<Comida> comidas = this.comidaService.getProductsByName(nombre);
        return new ResponseEntity<>(ComidaTransformer.toDTOList(comidas), HttpStatus.OK);
    }

}
