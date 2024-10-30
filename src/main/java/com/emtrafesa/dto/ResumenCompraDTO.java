package com.emtrafesa.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResumenCompraDTO {
    private String origen;
    private String destino;
    private String horaSalida;
    private String tipoBus;
    private boolean tieneEscalas;
    private List<AsientoDetalleDTO> asientos;
    private Double total;
    private String metodoPago;
}
