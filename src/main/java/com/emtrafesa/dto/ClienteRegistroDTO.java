package com.emtrafesa.dto;

import com.emtrafesa.model.enums.TipoDocumento;
import com.emtrafesa.model.enums.TipoTelefono;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteRegistroDTO {
    @NotBlank(message = "Campo obligatorio")
    private String nombre;

    @NotBlank(message = "Campo obligatorio")
    private String apellidos;

    @NotBlank(message = "Campo obligatorio")
    @Email(message = "El formato del correo es inválido")
    private String correo;

    @NotBlank(message = "Campo obligatorio")
    @Size(min = 8, message = "La contraseña debe tener 8 caracteres como mínimo,")
    private String contrasena;

    @NotNull(message = "Campo obligatorio")
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "Campo obligatorio")
    private String numeroDocumento;

    @NotNull(message = "Campo obligatorio")
    private TipoTelefono tipoTelefono;

    @NotBlank(message = "Campo obligatorio")
    private String numeroTelefono;
}