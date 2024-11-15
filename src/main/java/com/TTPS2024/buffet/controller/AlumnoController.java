package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.request.usuario.AlumnoRequest;
import com.TTPS2024.buffet.model.usuario.Alumno;
import com.TTPS2024.buffet.service.usuario.AlumnoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class AlumnoController {

    @Autowired
    public AlumnoService alumnoService;

    @PostMapping("/registrar")
    public ResponseEntity<Alumno> save(@RequestBody AlumnoRequest alumnoRequest){
        return new ResponseEntity<>(this.alumnoService.save(alumnoRequest), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Alumno> update(@PathVariable("id") Long id, @RequestBody AlumnoRequest alumnoRequest){
        return new ResponseEntity<>(this.alumnoService.update(id, alumnoRequest), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Alumno>> getAlumnos(){
        return new ResponseEntity<>(this.alumnoService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/listarPorDni/{dni}")
    public ResponseEntity<Alumno> getAlumnosByDni(@PathVariable Integer dni){
        Alumno alumno = this.alumnoService.getUserByDni(dni);
        return (alumno != null) ? new ResponseEntity<>(alumno, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/listarPorEmail")
    public ResponseEntity<Alumno> getAlumnoByEmail(@PathParam("email") String email){
        Alumno alumno = this.alumnoService.getUserByEmail(email);
        return (alumno != null) ? new ResponseEntity<>(alumno, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listarPorNombre")
    public ResponseEntity<List<Alumno>> getAlumnosByName(@PathParam("nombre") String nombre){
        return new ResponseEntity<>(this.alumnoService.getUsersByName(nombre), HttpStatus.OK);
    }

    @GetMapping("/listarPorApellido")
    public ResponseEntity<List<Alumno>> getAlumnosByLastName(@PathParam("apellido") String apellido){
        return new ResponseEntity<>(this.alumnoService.getUsersByLastName(apellido), HttpStatus.OK);
    }
    @GetMapping("/listarHabilitados")
    public ResponseEntity<List<Alumno>> getAlumnosEnabled(){
        return new ResponseEntity<>(this.alumnoService.getAlumnosByEnabled(), HttpStatus.OK);
    }



}
