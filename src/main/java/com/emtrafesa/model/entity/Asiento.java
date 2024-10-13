package com.emtrafesa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "asiento", indexes = {
        @Index(name = "idx_estado", columnList = "estado")
})
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asiento")
    private Long id;

    @Column(name = "numero_asiento", nullable = false)
    private int numeroAsiento;

    @Column(name = "piso", nullable = false)  // Asegúrate de tener esta columna
    private int piso;

    @Column(name = "estado", nullable = false)
    private String estado; // Puede ser "OCUPADO", "RESERVADO", "DISPONIBLE"

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;
}
