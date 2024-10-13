package com.emtrafesa.model.entity;

import com.emtrafesa.model.enums.MetodoPago;


import com.emtrafesa.model.enums.TipoServicio;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;

    @ManyToOne //verificar
    @JoinColumn(name = "pasaje_id")
    private Pasaje pasaje;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(name = "fecha_pago")
    private Date fechaPago;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "estado_pago")
    private String estadoPago;
}

