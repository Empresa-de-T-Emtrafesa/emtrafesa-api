package com.emtrafesa.model.entity;

import com.emtrafesa.model.enums.MetodoPago;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "historial_pago")
public class HistorialPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "pasaje_id")
    private Pasaje pasaje;

    @Column(name = "monto_pagado", nullable = false)
    private Double montoPagado;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "fecha_pago", nullable = false)
    private Date fechaPago;

    @Column(name = "estado_pago", nullable = false)
    private String estadoPago;

    @Column(name = "transaccion_id")
    private String transaccionId;
}
