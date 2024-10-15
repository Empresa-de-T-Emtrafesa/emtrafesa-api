package com.emtrafesa.dto;

import com.emtrafesa.model.enums.TipoUsuario;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IniciarSesionRespuestaDTO {
    private String mensaje;
    private TipoUsuario tipoUsuario;
}
