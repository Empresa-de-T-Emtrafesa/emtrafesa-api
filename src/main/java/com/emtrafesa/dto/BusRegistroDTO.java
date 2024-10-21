package com.emtrafesa.dto;

import com.emtrafesa.model.enums.TipoServicio;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class BusRegistroDTO {
    @NotBlank(message = "La placa es obligatoria")
    private String placa;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @NotNull(message = "El servicio es obligatorio")
    private TipoServicio tipoServicio;

    @Min(value = 1, message = "El número de pisos debe ser mayor a 0.")
    private int numeroPisos;

    @Min(value = 1, message = "La cantidad de asientos por piso debe ser mayor a 0.")
    private Map<Integer, Integer> cantidadAsientosPorPiso;
}