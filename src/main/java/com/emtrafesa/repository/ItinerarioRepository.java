package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {
    List<Itinerario> findByRuta_OrigenAndRuta_DestinoAndFechaViaje(String origen, String destino, LocalDate fechaViaje);
}