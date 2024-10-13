package com.emtrafesa.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "postergacion")
public class Postergacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pasaje_id")
    private Pasaje pasaje;

    @ManyToOne
    @JoinColumn(name = "nuevo_itinerario_id")
    private Itinerario nuevoItinerario;

    @Column(name = "fecha_postergacion", nullable = false)
    private Date fechaPostergacion;
}