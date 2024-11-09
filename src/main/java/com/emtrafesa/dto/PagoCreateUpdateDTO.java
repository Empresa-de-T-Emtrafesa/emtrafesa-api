package com.emtrafesa.dto;

import com.emtrafesa.model.enums.MetodoPago;
import lombok.Data;

import java.util.Date;

@Data
public class PagoCreateUpdateDTO {
    private Long pasajeId;
    private Long clienteId;
    private Long empresaId;
    private Date fechaPago;
    private Double monto;
    private MetodoPago metodoPago;
    private String estadoPago;
}