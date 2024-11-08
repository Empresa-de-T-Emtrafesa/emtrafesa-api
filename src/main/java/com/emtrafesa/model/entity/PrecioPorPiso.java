package com.emtrafesa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "precio_por_piso", uniqueConstraints = {@UniqueConstraint(columnNames = {"itinerario_id", "piso"})})
public class PrecioPorPiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precio_piso")
    private Long id;

    @Column(name = "piso")
    private int piso;

    @Column(name = "precio")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "itinerario_id")
    private Itinerario itinerario;
}