package com.emtrafesa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "ruta", indexes = {
        @Index(name = "idx_origen_destino", columnList = "origen, destino")
})

public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origen", nullable = false)
    private String origen;

    @Column(name = "destino", nullable = false)
    private String destino;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Itinerario> itinerarios;
}