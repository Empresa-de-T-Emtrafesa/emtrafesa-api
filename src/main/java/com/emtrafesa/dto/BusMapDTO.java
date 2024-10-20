package com.emtrafesa.dto;

import lombok.Data;
import java.util.List;

@Data
public class BusMapDTO {
    private Long busId;
    private String placa;
    private List<AsientoDTO> asientos;
}
