package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.dto.carta.producto.MenuDTO;
import com.TTPS2024.buffet.controller.request.carta.producto.MenuRequest;
import com.TTPS2024.buffet.helper.transformer.carta.producto.MenuTransformer;
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
        Menu menu = this.menuService.save(menuRequest);
        return new ResponseEntity<>(MenuTransformer.toDTO(menu), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<MenuDTO> update(@PathVariable Long id,@RequestBody MenuRequest menuRequest){
        Menu menu = this.menuService.update(id, menuRequest);
        return (menu != null) ? new ResponseEntity<>(MenuTransformer.toDTO(menu), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MenuDTO>> getMenus(){
        List<Menu> menues = this.menuService.getAll();
        return new ResponseEntity<>(MenuTransformer.toDTOList(menues), HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<MenuDTO> getMenu(@PathVariable("id") Long id){
        Menu menu = this.menuService.getProductById(id);
        return new ResponseEntity<>(MenuTransformer.toDTO(menu), HttpStatus.OK);
    }

}
