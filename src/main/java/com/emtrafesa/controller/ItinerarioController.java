package com.emtrafesa.controller;

import com.emtrafesa.dto.BusRegistroDTO;
import com.emtrafesa.dto.ItinerarioDTO;
import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.repository.ItinerarioRepository;
import com.emtrafesa.service.ItinerarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/itinerarios")
@CrossOrigin(origins = "http://localhost:4200") // URL del front-end
public class ItinerarioController {

    @Autowired
    private ItinerarioService itinerarioService;



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
    public List<Itinerario> listarItinerarios() {return itinerarioService.listarItinerario();}
}