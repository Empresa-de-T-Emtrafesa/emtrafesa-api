package com.emtrafesa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RutaDTO {
    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    private Boolean tieneEscalas; // Puede ser nulo, si no es requerido
    private Long empleadoId; // ID del empleado encargado
}