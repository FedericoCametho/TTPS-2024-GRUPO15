package com.TTPS2024.buffet.controller;


import com.TTPS2024.buffet.controller.dto.SugerenciaDTO;
import com.TTPS2024.buffet.controller.request.sugerencia.SugerenciaRequest;
import com.TTPS2024.buffet.helper.transformer.sugerencia.SugerenciaTransformer;
import com.TTPS2024.buffet.model.sugerencia.Sugerencia;
import com.TTPS2024.buffet.model.sugerencia.TipoSugerencia;
import com.TTPS2024.buffet.service.sugerencia.SugerenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sugerencia")
public class SugerenciaController {
    private SugerenciaService sugerenciaService;

    @Autowired
    public SugerenciaController(SugerenciaService sugerenciaService) {
        this.sugerenciaService = sugerenciaService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<SugerenciaDTO> save(@RequestBody SugerenciaRequest request) {
        Sugerencia sugerencia = this.sugerenciaService.save(request);
        return new ResponseEntity<>(SugerenciaTransformer.toDTO(sugerencia), HttpStatus.OK);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<SugerenciaDTO>> getSugerencias() {
        List<Sugerencia> sugerencias = this.sugerenciaService.getAll();
        return new ResponseEntity<>(SugerenciaTransformer.toDTOList(sugerencias), HttpStatus.OK);
    }

    @GetMapping("/listarPorTipo")
    public ResponseEntity<List<SugerenciaDTO>> getSugerenciasByTipo(@RequestParam TipoSugerencia tipo) {
        List<Sugerencia> sugerencias = this.sugerenciaService.getSugerenciaByTipo(tipo);
        return new ResponseEntity<>(SugerenciaTransformer.toDTOList(sugerencias), HttpStatus.OK);
    }

}
