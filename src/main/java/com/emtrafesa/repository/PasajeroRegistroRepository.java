package com.emtrafesa.repository;
import com.emtrafesa.model.entity.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasajeroRegistroRepository extends JpaRepository<Pasajero,Long> {
    Optional<Pasajero> findByNombreAndApellidos(String nombre, String apellidos);
    Optional<Pasajero> findByNumeroDocumento(String numeroDocumento);
}
