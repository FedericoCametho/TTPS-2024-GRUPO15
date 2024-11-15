package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.dto.MenuDTO;
import com.TTPS2024.buffet.controller.request.carta.producto.MenuRequest;
import com.TTPS2024.buffet.model.carta.producto.Menu;
import com.TTPS2024.buffet.service.carta.producto.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    public MenuService menuService;

    @PostMapping("/agregar")
    public ResponseEntity<MenuDTO> save(@RequestBody MenuRequest menuRequest){
        return new ResponseEntity<>(this.menuService.save(menuRequest), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<MenuDTO> update(@PathVariable Long id,@RequestBody MenuRequest menuRequest){
        MenuDTO menu = this.menuService.update(id, menuRequest);
        return (menu != null) ? new ResponseEntity<>(menu, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MenuDTO>> getMenus(){
        return new ResponseEntity<>(this.menuService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.menuService.getProductById(id), HttpStatus.OK);
    }

}
