package com.emtrafesa.controller;

import com.emtrafesa.dto.BusMapDTO;
import com.emtrafesa.service.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asientos")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;
    // ola
    @GetMapping("/bus/{busId}/mapa")
    public ResponseEntity<BusMapDTO> getMapaAsientos(@PathVariable Long busId) {
        BusMapDTO mapa = asientoService.getMapaAsientos(busId);
        return ResponseEntity.ok(mapa);
    }

}
