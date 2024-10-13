package com.emtrafesa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pasaje_pasajero")

public class PasajePasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasaje_pasajeo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pasaje_id")
    private Pasaje pasaje;

    @ManyToOne
    @JoinColumn(name = "pasajero_id")
    private Pasajero pasajero;
}

