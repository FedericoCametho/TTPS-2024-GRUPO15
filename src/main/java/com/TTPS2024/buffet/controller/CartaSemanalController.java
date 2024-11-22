package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.dto.carta.CartaSemanalDTO;
import com.TTPS2024.buffet.controller.request.carta.CartaSemanalRequest;
import com.TTPS2024.buffet.helper.transformer.carta.CartaSemanalTransformer;
import com.TTPS2024.buffet.model.carta.CartaSemanal;
import com.TTPS2024.buffet.service.carta.CartaSemanalService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartaSemanal")
public class CartaSemanalController {
    @Autowired
    public CartaSemanalService cartaSemanalService;

    @PostMapping("/agregar")
    public ResponseEntity<CartaSemanalDTO> save(@RequestBody CartaSemanalRequest cartaSemanalRequest){
        CartaSemanal cartaSemanal = this.cartaSemanalService.save(cartaSemanalRequest);
        return new ResponseEntity<>(CartaSemanalTransformer.toDTO(cartaSemanal), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CartaSemanalDTO> update(@PathVariable Long id,@RequestBody CartaSemanalRequest cartaSemanalRequest){
        CartaSemanal cartaSemanal = this.cartaSemanalService.update(id, cartaSemanalRequest);
        return (cartaSemanal != null) ? new ResponseEntity<>(CartaSemanalTransformer.toDTO(cartaSemanal), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CartaSemanalDTO>> getCartasSemanal(){
        List<CartaSemanal> cartasSemanal = this.cartaSemanalService.getAll();
        return new ResponseEntity<>(CartaSemanalTransformer.toDTOList(cartasSemanal), HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<CartaSemanalDTO> getCartaSemanalById(@PathVariable("id") Long id){
        CartaSemanal cartaSemanal = this.cartaSemanalService.getById(id);
        return new ResponseEntity<>(CartaSemanalTransformer.toDTO(cartaSemanal), HttpStatus.OK);
    }

    @GetMapping("/listarPorNombre")
    public ResponseEntity<CartaSemanalDTO> getCartaSemanalByName(@PathParam("nombre") String nombre){
        CartaSemanal cartaSemanal = this.cartaSemanalService.getByNombre(nombre);
        return new ResponseEntity<>(CartaSemanalTransformer.toDTO(cartaSemanal), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Long> deleteCartaSemanal(@PathVariable("id") Long id){
        this.cartaSemanalService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
