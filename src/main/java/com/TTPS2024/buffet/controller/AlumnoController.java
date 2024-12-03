package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.dto.usuario.AlumnoDTO;
import com.TTPS2024.buffet.controller.request.usuario.LoginRequest;
import com.TTPS2024.buffet.controller.request.usuario.create.AlumnoRequest;
import com.TTPS2024.buffet.controller.request.usuario.update.AlumnoRequestUpdate;
import com.TTPS2024.buffet.helper.transformer.usuario.AlumnoTransformer;
import com.TTPS2024.buffet.model.usuario.Alumno;
import com.TTPS2024.buffet.service.usuario.AlumnoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    public AlumnoService alumnoService;

    @PostMapping("/registrar")
    public ResponseEntity<AlumnoDTO> save(@RequestBody AlumnoRequest alumnoRequest){
        return new ResponseEntity<>(AlumnoTransformer.toDTO(this.alumnoService.save(alumnoRequest)), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<AlumnoDTO> update(@PathVariable("id") Long id, @RequestBody AlumnoRequestUpdate alumnoRequestupdate){
        return new ResponseEntity<>(AlumnoTransformer.toDTO(this.alumnoService.update(id, alumnoRequestupdate)), HttpStatus.OK);
    }
    @PutMapping("/actualizar-contrasena/{id}")
    public ResponseEntity<AlumnoDTO> updatePassword(@PathVariable("id") Long id, @RequestParam("contrasena") String contrasena){
        return new ResponseEntity<>(AlumnoTransformer.toDTO(this.alumnoService.updatePassword(id, contrasena)), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AlumnoDTO>> getAlumnos(){
        return new ResponseEntity<>(AlumnoTransformer.toDTOList(this.alumnoService.getAll()), HttpStatus.OK);
    }
    @GetMapping("/listarPorDni/{dni}")
    public ResponseEntity<AlumnoDTO> getAlumnosByDni(@PathVariable Integer dni){
        Alumno alumno = this.alumnoService.getUserByDni(dni);
        return (alumno != null) ? new ResponseEntity<>(AlumnoTransformer.toDTO(alumno), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/listarPorEmail")
    public ResponseEntity<AlumnoDTO> getAlumnoByEmail(@PathParam("email") String email){
        Alumno alumno = this.alumnoService.getUserByEmail(email);
        return (alumno != null) ? new ResponseEntity<>(AlumnoTransformer.toDTO(alumno), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listarPorNombre")
    public ResponseEntity<List<AlumnoDTO>> getAlumnosByName(@PathParam("nombre") String nombre){
        return new ResponseEntity<>(AlumnoTransformer.toDTOList(this.alumnoService.getUsersByName(nombre)), HttpStatus.OK);
    }

    @GetMapping("/listarPorApellido")
    public ResponseEntity<List<AlumnoDTO>> getAlumnosByLastName(@PathParam("apellido") String apellido){
        return new ResponseEntity<>(AlumnoTransformer.toDTOList(this.alumnoService.getUsersByLastName(apellido)), HttpStatus.OK);
    }
    @GetMapping("/listarHabilitados")
    public ResponseEntity<List<AlumnoDTO>> getAlumnosEnabled(){
        return new ResponseEntity<>(AlumnoTransformer.toDTOList(this.alumnoService.getAlumnosByEnabled()), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AlumnoDTO> login(@RequestBody LoginRequest loginRequest) {
        try {
            Alumno response = this.alumnoService.login(loginRequest);
            return ResponseEntity.ok(AlumnoTransformer.toDTO(response));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
