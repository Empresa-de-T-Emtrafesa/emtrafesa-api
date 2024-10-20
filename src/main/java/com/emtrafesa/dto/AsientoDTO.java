package com.emtrafesa.dto;

import lombok.Data;

@Data
public class AsientoDTO {
    private Long id;
    private int numeroAsiento;
    private int piso;
    private String estado; // "OCUPADO", "DISPONIBLE"
    private Long busId;
}
