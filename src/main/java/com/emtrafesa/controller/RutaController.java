package com.emtrafesa.controller;

import com.emtrafesa.dto.RutaDTO;
import com.emtrafesa.model.entity.Ruta;
import com.emtrafesa.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/rutas")
@CrossOrigin(origins = "http://localhost:4200")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @PostMapping("/crear")
    public ResponseEntity<Ruta> crearRuta(@RequestBody RutaDTO rutaDTO) {
        Ruta nuevaRuta = rutaService.crearRuta(rutaDTO);
        return ResponseEntity.ok(nuevaRuta);
    }

    @GetMapping("/origenes")
    public ResponseEntity<Set<String>> obtenerOrígenes() {
        Set<String> orígenes = rutaService.obtenerOrígenes();
        return ResponseEntity.ok(orígenes);
    }

    @GetMapping("/destinos")
    public ResponseEntity<Set<String>> obtenerDestinos() {
        Set<String> destinos = rutaService.obtenerDestinos();
        return ResponseEntity.ok(destinos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
        return ResponseEntity.ok("Ruta eliminada");
    }
}