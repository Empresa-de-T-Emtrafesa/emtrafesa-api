package com.emtrafesa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoReportDTO {
    private Double montoTotal;
    private String fechaReporte;
    private Long cantidadPagos;
}
