package com.emtrafesa.model.entity;

import com.emtrafesa.model.enums.EstadoBus;
import com.emtrafesa.model.enums.TipoServicio;
import com.emtrafesa.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;  // Importa la clase Map

@Data
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_bus")
    private Long id;

    @Column(name = "placa", nullable = false, unique = true)
    private String placa;
    //llamamos enum
    @Enumerated(EnumType.STRING)
    @Column(name ="tipo_servicio", nullable = false)
    private TipoServicio servicio;

    @Column(name ="modelo")
    private String modelo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_bus", length = 20)
    private EstadoBus estadoBus; //HABILITADO O INHABILITADO POR MANTENIMIENTO

    @Column(name ="numero_pisos")
    private Integer numeroPisos;

    //se crean arreglos
    @ElementCollection
    @CollectionTable(name = "asientos_por_piso", joinColumns = @JoinColumn(name = "bus_id"))
    @MapKeyColumn(name = "piso")
    @Column(name = "cantidad_asientos")
    private Map<Integer, Integer> cantidadAsientosPorPiso;

}
