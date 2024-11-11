package com.emtrafesa.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class ItinerarioListarDTO {
    private RutaDTO rutaId;           // ID de la ruta
    private BusRegistroDTO busId;            // ID del bus
    private Boolean tieneEscalas; // Puede ser nulo, si no es requerido
    private LocalTime horaSalida;   // Hora de salida
    private LocalTime horaLlegada;  // Hora de llegada
    private List<DisponibilidadDTO> disponibilidades;  // Fecha del viaje
    private List<PrecioPorPisoDTO> precioPorPiso; // Lista de precios por piso
}