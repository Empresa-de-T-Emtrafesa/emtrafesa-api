package com.emtrafesa.controller;

import com.emtrafesa.dto.ResumenCompraDTO;
import com.emtrafesa.service.PasajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pasaje")
public class PasajeController {
    private final PasajeService pasajeService;

    @Autowired
    public PasajeController(PasajeService pasajeService) {
        this.pasajeService = pasajeService;
    }

    // Endpoint para generar el resumen de compra
    @GetMapping("/{id}/resumen")
    public ResponseEntity<ResumenCompraDTO> obtenerResumenCompra(@PathVariable Long id) {
        ResumenCompraDTO resumenCompra = pasajeService.generarResumenDeCompra(id);
        return ResponseEntity.ok(resumenCompra);
    }
}