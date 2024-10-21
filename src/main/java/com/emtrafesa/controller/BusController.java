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
        try{
            busService.registrarBus(busRegistroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bus registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Bus> listarBus(){return busService.listarBus(); }
}
