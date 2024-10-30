package com.emtrafesa.dto;

import com.emtrafesa.model.enums.SEXO;
import com.emtrafesa.model.enums.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.util.Date;

@Data
public class PasajeroRegistroDTO {
    @NotBlank(message = "Campo obligatorio")
    private String nombre;

    @NotBlank(message = "Campo obligatorio")
    private String apellidos;

    @NotNull(message = "Campo obligatorio")
    private SEXO sexo;

    @NotNull(message = "Campo obligatorio")
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "Campo obligatorio")
    private String numeroDocumento;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private Date fechaNacimiento;
}
