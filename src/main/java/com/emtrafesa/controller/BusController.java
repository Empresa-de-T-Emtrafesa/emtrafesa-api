package com.emtrafesa.controller;

import com.emtrafesa.dto.BusRegistroDTO;
import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.service.BusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/buses")
@RestController
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public ResponseEntity<String> registrarBus(@Valid @RequestBody BusRegistroDTO busRegistroDTO){
        busService.registrarBus(busRegistroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Bus registrado exitosamente.");
    }

    @GetMapping
    public List<Bus> listarBus(){return busService.listarBus(); }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarBus(@PathVariable Long id, @Valid @RequestBody BusRegistroDTO busRegistroDTO){
        busService.modificarBus(id, busRegistroDTO);
        return ResponseEntity.ok("Bus modificado exitosamente.");
    }
}