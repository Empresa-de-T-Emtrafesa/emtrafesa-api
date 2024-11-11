package com.emtrafesa.controller;

import com.emtrafesa.dto.ItinerarioDTO;
import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.service.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/itinerarios")
@CrossOrigin(origins = "http://localhost:4200") // URL del front-end
public class ItinerarioController {

    @Autowired
    private ItinerarioService itinerarioService;

    // Obtener destinos disponibles para un origen específico
    @GetMapping("/destinos")
    public ResponseEntity<List<String>> obtenerDestinosDisponibles(@RequestParam String origen) {
        List<String> destinos = itinerarioService.getDestinosDisponibles(origen);
        return new ResponseEntity<>(destinos, HttpStatus.OK);
    }

    @GetMapping("/origenes")
    public ResponseEntity<List<String>> obtenerOrigenesDisponibles(@RequestParam String destino) {
        List<String> origenes = itinerarioService.getOrigenesDisponibles(destino);
        return new ResponseEntity<>(origenes, HttpStatus.OK);
    }

    // Obtener fechas disponibles para origen y destino específicos
    @GetMapping("/fechas")
    public ResponseEntity<List<LocalDate>> obtenerFechasDisponibles(
            @RequestParam String origen, @RequestParam String destino) {
        List<LocalDate> fechas = itinerarioService.getFechasDisponibles(origen, destino);
        return new ResponseEntity<>(fechas, HttpStatus.OK);
    }


    @GetMapping("/filtrar")
    public List<ItinerarioDTO> filtrarItinerarios(
            @RequestParam String origen,
            @RequestParam String destino,
            @RequestParam LocalDate fechaViaje) {
        return itinerarioService.filtrarItinerarios(origen, destino, fechaViaje);
    }


    @PostMapping("/crear")
    public ResponseEntity<String> crearItinerario(@RequestBody ItinerarioDTO itinerarioDTO) {
        itinerarioService.crearItinerario(itinerarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Itinerario creado exitosamente");

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarItinerario(@PathVariable Long id, @RequestBody ItinerarioDTO itinerarioDTO) {
        itinerarioService.modificarItinerario(id, itinerarioDTO);
        return ResponseEntity.ok("Itinerario modificado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarItinerario(@PathVariable Long id) {
        itinerarioService.eliminarItinerario(id);
        return ResponseEntity.ok("Itinerario eliminado");
    }

    @GetMapping
    public List<ItinerarioDTO> listarItinerarios() {return itinerarioService.listarItinerario();}
}