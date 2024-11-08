package com.emtrafesa.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PrecioPorPisoDTO {
    private int piso;         // El número del piso

    @Min(value = 1, message = "El precio debe ser mayor a cero.")
    private double precio; // El precio para ese piso
}
