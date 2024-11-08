package com.emtrafesa.controller;

import com.emtrafesa.dto.RutaDTO;
import com.emtrafesa.dto.RutaResponseDTO;
import com.emtrafesa.model.entity.Ruta;
import com.emtrafesa.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    // Nuevo método para obtener destinos filtrados por origen
    @GetMapping("/destinos/{origen}")
    public ResponseEntity<Set<String>> obtenerDestinosPorOrigen(@PathVariable String origen) {
        Set<String> destinos = rutaService.obtenerDestinosPorOrigen(origen);
        return ResponseEntity.ok(destinos);
    }

    // Método para obtener todas las rutas
    @GetMapping
    public ResponseEntity<List<Ruta>> obtenerRutas() {
        List<Ruta> rutas = rutaService.obtenerRutas();
        return ResponseEntity.ok(rutas);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);  // Llamada al servicio para eliminar la ruta
        return ResponseEntity.noContent().build();  // Devuelve un status 204 (sin contenido)
    }


    // Método para editar una ruta
    @PutMapping("/{id}")
    public ResponseEntity<Ruta> editarRuta(@PathVariable Long id, @RequestBody RutaDTO rutaDTO) {
        Ruta rutaEditada = rutaService.editarRuta(id, rutaDTO);
        return ResponseEntity.ok(rutaEditada);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Ruta> actualizarRuta(@RequestBody Ruta ruta) {
        Ruta rutaActualizada = rutaService.actualizarRuta(ruta);
        if (rutaActualizada != null) {
            return ResponseEntity.ok(rutaActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}


