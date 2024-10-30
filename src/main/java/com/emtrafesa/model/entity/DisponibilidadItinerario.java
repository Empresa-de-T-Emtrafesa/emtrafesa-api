package com.emtrafesa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "disponibilidad_itinerario", indexes = {
        @Index(name = "idx_fecha_viaje", columnList = "fecha_viaje")
})
public class DisponibilidadItinerario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "itinerario_id")
     private Itinerario itinerario;

    @Column(name = "fecha_viaje")
     private LocalDate fechaViaje;
}
