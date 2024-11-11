package com.emtrafesa.dto;

import com.emtrafesa.model.enums.EstadoBus;
import com.emtrafesa.model.enums.TipoServicio;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class BusRegistroDTO {
    private Long idBus;
    private String placa;
    private String modelo;
    private TipoServicio tipoServicio;
    private EstadoBus estadoBus;
    @NotNull(message = "El número de pisos es obligatorio")
    private Integer numeroPisos;
    private Map<Integer, Integer> cantidadAsientosPorPiso;
}