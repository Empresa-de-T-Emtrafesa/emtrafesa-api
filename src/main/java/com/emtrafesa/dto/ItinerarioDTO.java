package com.emtrafesa.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class ItinerarioDTO {
    private Long rutaId;           // ID de la ruta
    private Long busId;            // ID del bus
    private LocalDate fechaViaje;  // Fecha del viaje
    private LocalTime horaSalida;   // Hora de salida
    private LocalTime horaLlegada;  // Hora de llegada
    private List<Double> preciosPorPiso; // Lista de precios por piso
}
