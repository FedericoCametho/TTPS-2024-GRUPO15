package com.TTPS2024.buffet.controller;

import com.TTPS2024.buffet.controller.request.usuario.LoginRequest;
import com.TTPS2024.buffet.service.usuario.AdministradorService;
import com.TTPS2024.buffet.service.usuario.AlumnoService;
import com.TTPS2024.buffet.service.usuario.ResponsableDeTurnoService;
import com.TTPS2024.buffet.service.usuario.login.Credential;
import com.TTPS2024.buffet.service.usuario.login.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
public class LoginController {

    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private ResponsableDeTurnoService responsableDeTurnoService;

    @Autowired
    private TokenService tokenServices;

    private final int EXPIRATION_IN_SEC = 3600;

    @PostMapping("/alumno")
    public ResponseEntity<?> authenticateAlumno(@RequestBody LoginRequest loginRequest){
        if(this.alumnoService.login(loginRequest)){
            String token = tokenServices.generateToken(loginRequest.getEmail(), EXPIRATION_IN_SEC);
            return ResponseEntity.ok(new Credential(token, EXPIRATION_IN_SEC, loginRequest.getEmail()));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }
    }

    @PostMapping("/responsableDeTurno")
    public ResponseEntity<?> authenticateResponsable(@RequestBody LoginRequest loginRequest){
        if(this.responsableDeTurnoService.login(loginRequest)){
            String token = tokenServices.generateToken(loginRequest.getEmail(), EXPIRATION_IN_SEC);
            return ResponseEntity.ok(new Credential(token, EXPIRATION_IN_SEC, loginRequest.getEmail()));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }
    }

    @PostMapping("/administrador")
    public ResponseEntity<?> authenticateAdmin(@RequestBody LoginRequest loginRequest){
        if(this.administradorService.login(loginRequest)){
            String token = tokenServices.generateToken(loginRequest.getEmail(), EXPIRATION_IN_SEC);
            return ResponseEntity.ok(new Credential(token, EXPIRATION_IN_SEC, loginRequest.getEmail()));
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }
    }


}
