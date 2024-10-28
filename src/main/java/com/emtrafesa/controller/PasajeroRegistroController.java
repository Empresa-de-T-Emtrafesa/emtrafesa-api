package com.emtrafesa.controller;
import com.emtrafesa.dto.PasajeroRegistroDTO;
import com.emtrafesa.model.entity.Pasajero;
import com.emtrafesa.service.PasajeroRegistroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pasajero")
public class PasajeroRegistroController {

    @Autowired
    private PasajeroRegistroService pasajeroRegistroService;

    @PostMapping
    public ResponseEntity<String> registrarPasajero(@Valid @RequestBody PasajeroRegistroDTO pasajeroRegistroDTO) {
            pasajeroRegistroService.registrarPasajero(pasajeroRegistroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pasajero registrado exitosamente.");
    }

    @GetMapping
    public List<Pasajero> listarPasajeros(){
        return  pasajeroRegistroService.listaPasajero();
    }
}