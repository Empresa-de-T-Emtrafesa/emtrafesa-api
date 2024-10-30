package com.emtrafesa.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.emtrafesa.model.entity.DisponiblidadItinerario;
import com.emtrafesa.model.entity.PrecioPorPiso;
import lombok.Data;

@Data
public class ItinerarioDTO {
    private Long rutaId;           // ID de la ruta
    private Long busId;            // ID del bus
    private Boolean tieneEscalas; // Puede ser nulo, si no es requerido
    private List<DisponiblidadItinerario> disponiblidades;  // Fecha del viaje
    private LocalTime horaSalida;   // Hora de salida
    private LocalTime horaLlegada;  // Hora de llegada
    private List<PrecioPorPiso> preciosPorPiso; // Lista de precios por piso
}