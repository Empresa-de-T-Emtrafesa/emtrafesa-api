package com.emtrafesa.dto;

import com.emtrafesa.model.enums.TipoDocumento;
import com.emtrafesa.model.enums.TipoTelefono;
import lombok.Data;

@Data
public class ClienteRegistroDTO {

    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private TipoTelefono tipoTelefono;
    private String numeroTelefono;

}
