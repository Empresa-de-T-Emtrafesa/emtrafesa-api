package com.emtrafesa.model.entity;

import java.time.LocalTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "itinerario")
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_itinerario")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Column(name = "tiene_escalas", nullable = false)
    private Boolean tieneEscalas;

    @OneToMany(mappedBy = "itinerario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrecioPorPiso> preciosPorPiso;

    @OneToMany(mappedBy = "itinerario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DisponibilidadItinerario> disponibilidades;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "hora_llegada")
    private LocalTime horaLlegada;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

}