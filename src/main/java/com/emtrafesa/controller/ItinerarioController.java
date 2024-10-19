package com.emtrafesa.controller;

import com.emtrafesa.dto.ItinerarioDTO;
import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.service.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/filtrar")
    public ResponseEntity<List<Itinerario>> filtrarItinerarios(
            @RequestParam(required = false) String origen,
            @RequestParam(required = false) String destino,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaViaje) {

        if (origen == null || destino == null || fechaViaje == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Itinerario> itinerarios = itinerarioService.filtrarItinerariosPorOrigenDestino(origen, destino, fechaViaje);
        return ResponseEntity.ok(itinerarios);
    }


    @PostMapping("/crear")
    public ResponseEntity<Itinerario> crearItinerario(@RequestBody ItinerarioDTO itinerarioDTO) {
        Itinerario nuevoItinerario = itinerarioService.crearItinerario(itinerarioDTO);
        return ResponseEntity.ok(nuevoItinerario);
    }


}