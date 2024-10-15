package com.emtrafesa.controller;
import com.emtrafesa.dto.PasajeroRegistroDTO;
import com.emtrafesa.model.entity.Pasajero;
import com.emtrafesa.service.PasajeroRegistroService;
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
    public ResponseEntity<String> registrarPasajero(@RequestBody PasajeroRegistroDTO pasajeroRegistroDTO) {
        try {
            pasajeroRegistroService.registrarPasajero(pasajeroRegistroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pasajero registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Pasajero> listarClientes(){
        return  pasajeroRegistroService.listaPasajero();
    }
}