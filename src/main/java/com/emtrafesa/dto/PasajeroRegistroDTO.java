package com.emtrafesa.dto;

import com.emtrafesa.model.enums.SEXO;
import com.emtrafesa.model.enums.TipoDocumento;
import lombok.Data;

import java.util.Date;

@Data
public class PasajeroRegistroDTO {
    private String nombre;
    private String apellidos;
    private SEXO sexo;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private Date fechaNacimiento;
}
