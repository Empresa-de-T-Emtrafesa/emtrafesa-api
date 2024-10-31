package com.emtrafesa.dto;

import java.time.LocalTime;
import java.util.List;

import com.emtrafesa.model.entity.DisponibilidadItinerario;
import lombok.Data;

@Data
public class ItinerarioDTO {
    private Long rutaId;           // ID de la ruta
    private Long busId;            // ID del bus
    private Boolean tieneEscalas; // Puede ser nulo, si no es requerido
    private LocalTime horaSalida;   // Hora de salida
    private LocalTime horaLlegada;  // Hora de llegada
    private List<DisponibilidadDTO> disponibilidades;  // Fecha del viaje
    private List<PrecioPorPisoDTO> precioPorPiso; // Lista de precios por piso
}