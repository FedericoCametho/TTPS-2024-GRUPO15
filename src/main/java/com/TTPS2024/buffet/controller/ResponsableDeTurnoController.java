package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.dto.usuario.ResponsableDeTurnoDTO;
import com.TTPS2024.buffet.controller.request.usuario.LoginRequest;
import com.TTPS2024.buffet.controller.request.usuario.create.ResponsableDeTurnoRequest;
import com.TTPS2024.buffet.controller.request.usuario.update.ResponsableDeTurnoRequestUpdate;
import com.TTPS2024.buffet.helper.transformer.usuario.ResponsableDeTurnoTransformer;
import com.TTPS2024.buffet.model.usuario.ResponsableDeTurno;
import com.TTPS2024.buffet.model.usuario.Turno;
import com.TTPS2024.buffet.service.usuario.ResponsableDeTurnoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ResponsableDeTurno")
public class ResponsableDeTurnoController {

    @Autowired
    public ResponsableDeTurnoService ResponsableDeTurnoService;

    @PostMapping("/registrar")
    public ResponseEntity<ResponsableDeTurnoDTO> save(@RequestBody ResponsableDeTurnoRequest ResponsableDeTurnoRequest){
        return new ResponseEntity<>(ResponsableDeTurnoTransformer.toDTO(this.ResponsableDeTurnoService.save(ResponsableDeTurnoRequest)), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ResponsableDeTurnoDTO> update(@PathVariable("id") Long id, @RequestBody ResponsableDeTurnoRequestUpdate ResponsableDeTurnoRequestupdate){
        return new ResponseEntity<>(ResponsableDeTurnoTransformer.toDTO(this.ResponsableDeTurnoService.update(id, ResponsableDeTurnoRequestupdate)), HttpStatus.OK);
    }
    @PutMapping("/actualizar-contrasena/{id}")
    public ResponseEntity<ResponsableDeTurnoDTO> updatePassword(@PathVariable("id") Long id, @RequestParam("contrasena") String contrasena){
        return new ResponseEntity<>(ResponsableDeTurnoTransformer.toDTO(this.ResponsableDeTurnoService.updatePassword(id, contrasena)), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ResponsableDeTurnoDTO>> getResponsableDeTurnos(){
        return new ResponseEntity<>(ResponsableDeTurnoTransformer.toDTOList(this.ResponsableDeTurnoService.getAll()), HttpStatus.OK);
    }
    @GetMapping("/listarPorDni/{dni}")
    public ResponseEntity<ResponsableDeTurnoDTO> getResponsableDeTurnosByDni(@PathVariable Integer dni){
        ResponsableDeTurno ResponsableDeTurno = this.ResponsableDeTurnoService.getUserByDni(dni);
        return (ResponsableDeTurno != null) ? new ResponseEntity<>(ResponsableDeTurnoTransformer.toDTO(ResponsableDeTurno), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/listarPorEmail")
    public ResponseEntity<ResponsableDeTurnoDTO> getResponsableDeTurnoByEmail(@PathParam("email") String email){
        ResponsableDeTurno ResponsableDeTurno = this.ResponsableDeTurnoService.getUserByEmail(email);
        return (ResponsableDeTurno != null) ? new ResponseEntity<>(ResponsableDeTurnoTransformer.toDTO(ResponsableDeTurno), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listarPorNombre")
    public ResponseEntity<List<ResponsableDeTurnoDTO>> getResponsableDeTurnosByName(@PathParam("nombre") String nombre){
        return new ResponseEntity<>(ResponsableDeTurnoTransformer.toDTOList(this.ResponsableDeTurnoService.getUsersByName(nombre)), HttpStatus.OK);
    }

    @GetMapping("/listarPorApellido")
    public ResponseEntity<List<ResponsableDeTurnoDTO>> getResponsableDeTurnosByLastName(@PathParam("apellido") String apellido){
        return new ResponseEntity<>(ResponsableDeTurnoTransformer.toDTOList(this.ResponsableDeTurnoService.getUsersByLastName(apellido)), HttpStatus.OK);
    }
    @GetMapping("/listarPorTurno")
    public ResponseEntity<List<ResponsableDeTurnoDTO>> getResponsableDeTurnosByTurno(@RequestParam("turno") Turno turno){
        return new ResponseEntity<>(ResponsableDeTurnoTransformer.toDTOList(this.ResponsableDeTurnoService.getResponsablesDeTurnoByTurno(turno)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponsableDeTurnoDTO> login(@RequestBody LoginRequest loginRequest) {
        try {
            ResponsableDeTurno response = this.ResponsableDeTurnoService.login(loginRequest);
            return ResponseEntity.ok(ResponsableDeTurnoTransformer.toDTO(response));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
