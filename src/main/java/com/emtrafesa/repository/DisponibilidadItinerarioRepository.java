package com.emtrafesa.repository;

import com.emtrafesa.model.entity.DisponibilidadItinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DisponibilidadItinerarioRepository extends JpaRepository<DisponibilidadItinerario, Long> {
    @Query("SELECT DISTINCT d.fechaViaje FROM DisponibilidadItinerario d " +
            "JOIN d.itinerario i JOIN i.ruta r " +
            "WHERE r.origen = :origen AND r.destino = :destino")
    List<LocalDate> findFechasDisponiblesByOrigenDestino(String origen, String destino);
}
