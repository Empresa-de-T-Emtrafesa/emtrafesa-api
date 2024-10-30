package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    Optional<Ruta> findByOrigenAndDestino(String origen, String destino);
}