package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.dto.carta.CartaDelDiaDTO;
import com.TTPS2024.buffet.controller.request.carta.CartaDelDiaRequest;
import com.TTPS2024.buffet.helper.transformer.carta.CartaDelDiaTransformer;
import com.TTPS2024.buffet.model.carta.CartaDelDia;
import com.TTPS2024.buffet.service.carta.CartaDelDiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cartaDelDia")
public class CartaDelDiaController {
    private CartaDelDiaService cartaDelDiaService;
    @Autowired
    public CartaDelDiaController(CartaDelDiaService cartaDelDiaService) {
        this.cartaDelDiaService = cartaDelDiaService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<CartaDelDiaDTO> save(@RequestBody CartaDelDiaRequest request) {
        CartaDelDia cartaDelDia = this.cartaDelDiaService.save(request);
        return new ResponseEntity<>(CartaDelDiaTransformer.toDTO(cartaDelDia), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CartaDelDiaDTO> update(@PathVariable("id") Long id, @RequestBody CartaDelDiaRequest request) {
        CartaDelDia cartaDelDia = cartaDelDiaService.update(id, request);
        return (cartaDelDia != null) ? new ResponseEntity<>(CartaDelDiaTransformer.toDTO(cartaDelDia), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CartaDelDiaDTO>> getCartasDelDia() {
        List<CartaDelDia> cartaDelDias = this.cartaDelDiaService.getAll();
        return new ResponseEntity<>(CartaDelDiaTransformer.toDTOList(cartaDelDias),HttpStatus.OK);
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<CartaDelDiaDTO> getCartaDelDia(@PathVariable("id") Long id) {
        CartaDelDia cartaDelDia = this.cartaDelDiaService.getById(id);
        return new ResponseEntity<>(CartaDelDiaTransformer.toDTO(cartaDelDia), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Long> deleteCartaDelDia(@PathVariable("id") Long id) {
        cartaDelDiaService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<CartaDelDiaDTO> activateCartaDelDia(@PathVariable("id") Long id) {
        CartaDelDia result = cartaDelDiaService.activate(id);
        return new ResponseEntity<>(CartaDelDiaTransformer.toDTO(result), HttpStatus.OK);
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<CartaDelDiaDTO> deactivateCartaDelDia(@PathVariable("id") Long id) {
        CartaDelDia result = cartaDelDiaService.deactivate(id);
        return new ResponseEntity<>(CartaDelDiaTransformer.toDTO(result), HttpStatus.OK);
    }

}
