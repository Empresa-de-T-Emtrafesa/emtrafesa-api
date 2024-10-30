package com.emtrafesa.model.entity;

import com.emtrafesa.model.enums.TipoDocumento;
import com.emtrafesa.model.enums.TipoTelefono;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_cliente")
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellidos", length = 150, nullable = false)
    private String apellidos;

    @OneToOne
    @JoinColumn(name = "user_emtraf_id", referencedColumnName = "id_user_emtraf")
    private UserEmtraf userEmtraf;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento", length=20, unique = true, nullable = false)
    private String numeroDocumento;

    @Enumerated(EnumType.STRING)
    @Column(name ="telefono",length = 15, nullable = false)
    private TipoTelefono tipoTelefono;

    @Column(name = "numero_telefono", length = 10, nullable = false)
    private String numeroTelefono;

}





