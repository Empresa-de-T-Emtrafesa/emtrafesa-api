package com.emtrafesa.model.entity;

import com.emtrafesa.model.enums.SEXO;


import com.emtrafesa.model.enums.TipoDocumento;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "pasajero")
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", length = 70, nullable = false)
    private String nombre;

    @Column(name = "apellidos", length = 70, nullable = false)
    private String apellidos;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private SEXO sexo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento", length = 20, nullable = false)
    private String numeroDocumento;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;
}