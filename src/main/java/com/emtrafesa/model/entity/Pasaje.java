package com.emtrafesa.model.entity;

import com.emtrafesa.model.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pasaje")
public class Pasaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasaje")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "itinerario_id")
    private Itinerario itinerario;

    @JoinColumn(name = "fecha_compra")
    private Date fechaCompra;

    @JoinColumn(name = "total")
    private Double total;

    @JoinColumn(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "pasaje", cascade = CascadeType.ALL)
    private List<PasajePasajero> pasajePasajeros; // Relación con los pasajeros y asientos


    @OneToOne
    @JoinColumn(name = "postergacion_id")
    private Postergacion postergacion;

    @OneToOne
    @JoinColumn(name = "pago_id")
    private Pago pago;

}
