package com.emtrafesa.dto;

import com.emtrafesa.model.enums.TipoUsuario;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IniciarSesionRespuestaDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String token;
    private TipoUsuario tipoUsuario;
}