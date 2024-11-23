package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.dto.usuario.AdministradorDTO;
import com.TTPS2024.buffet.controller.request.usuario.LoginRequest;
import com.TTPS2024.buffet.controller.request.usuario.create.AdministradorRequest;
import com.TTPS2024.buffet.controller.request.usuario.update.AdministradorRequestUpdate;
import com.TTPS2024.buffet.helper.transformer.usuario.AdministradorTransformer;
import com.TTPS2024.buffet.model.usuario.Administrador;
import com.TTPS2024.buffet.service.usuario.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    public AdministradorService administradorService;

    @PostMapping("/registrar")
    public ResponseEntity<AdministradorDTO> save(@RequestBody AdministradorRequest administradorRequest){
        return new ResponseEntity<>(AdministradorTransformer.toDTO(this.administradorService.save(administradorRequest)), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AdministradorDTO>> getAdministradores(){
        return new ResponseEntity<>(AdministradorTransformer.toDTOList(this.administradorService.getAll()), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AdministradorDTO> login(@RequestBody LoginRequest loginRequest){
        try{
            Administrador response = this.administradorService.login(loginRequest);
            return ResponseEntity.ok(AdministradorTransformer.toDTO(response));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AdministradorDTO> update(@PathVariable("id") Long id, @RequestBody AdministradorRequestUpdate administradorRequestUpdate){
        return new ResponseEntity<>(AdministradorTransformer.toDTO(this.administradorService.update(id, administradorRequestUpdate)), HttpStatus.OK);
    }

    @PutMapping("/actualizar-contrasena/{id}")
    public ResponseEntity<AdministradorDTO> updatePassword(@PathVariable("id") Long id, @RequestBody String contrasena){
        return new ResponseEntity<>(AdministradorTransformer.toDTO(this.administradorService.updatePassword(id, contrasena)), HttpStatus.OK);
    }

    @GetMapping("/listarPorDni/{dni}")
    public ResponseEntity<AdministradorDTO> getAdministradorByDni(@PathVariable Integer dni){
        Administrador administrador = this.administradorService.getUserByDni(dni);
        return (administrador != null) ? new ResponseEntity<>(AdministradorTransformer.toDTO(administrador), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listarPorEmail")
    public ResponseEntity<AdministradorDTO> getAdministradorByEmail(@RequestParam("email") String email){
        Administrador administrador = this.administradorService.getUserByEmail(email);
        return (administrador != null) ? new ResponseEntity<>(AdministradorTransformer.toDTO(administrador), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listarPorNombre")
    public ResponseEntity<List<AdministradorDTO>> getAdministradoresByName(@RequestParam("nombre") String nombre){
        return new ResponseEntity<>(AdministradorTransformer.toDTOList(this.administradorService.getUsersByName(nombre)), HttpStatus.OK);
    }

    @GetMapping("/listarPorApellido")
    public ResponseEntity<List<AdministradorDTO>> getAdministradoresByLastName(@RequestParam("apellido") String apellido){
        return new ResponseEntity<>(AdministradorTransformer.toDTOList(this.administradorService.getUsersByLastName(apellido)), HttpStatus.OK);
    }


}
