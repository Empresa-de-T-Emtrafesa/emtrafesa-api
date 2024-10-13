package com.emtrafesa.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_emtraf_id", referencedColumnName = "id_user_emtraf")
    private UserEmtraf userEmtraf;

    @Column(name = "experiencia")
    private String experiencia;

}