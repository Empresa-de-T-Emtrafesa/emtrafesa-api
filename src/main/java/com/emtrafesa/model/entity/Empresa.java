package com.emtrafesa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long id;

    @Column(name = "nombre_empresa", length = 100, nullable = false)
    private String nombre_Empresa;

    @Column(name = "ruc", length = 11, unique = true, nullable = false)
    private String ruc;

    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private String telefono;


    @Column(name = "correo",length = 180, nullable = false, unique = true)
    private String correo;
}
