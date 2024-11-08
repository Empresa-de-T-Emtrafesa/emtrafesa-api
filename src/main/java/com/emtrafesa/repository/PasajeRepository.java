package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.model.entity.Pasaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasajeRepository extends JpaRepository<Pasaje, Long> {
    List<Pasaje> findByItinerario(Itinerario itinerario);
}