package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.model.entity.Ruta;
import com.emtrafesa.model.enums.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {
    List<Itinerario> findByBusId(Long busId);
    Itinerario findTopByBusIdOrderByHoraLlegadaDesc(Long busId);
    boolean existsByBus(Bus bus);

    // Consulta para obtener destinos válidos desde un origen
    @Query("SELECT DISTINCT r.destino FROM Itinerario i JOIN i.ruta r WHERE r.origen = :origen")
    List<String> findDistinctDestinosByOrigen(String origen);

    // Consulta para obtener orígenes válidos desde un destino
    @Query("SELECT DISTINCT r.origen FROM Itinerario i JOIN i.ruta r WHERE r.destino = :destino")
    List<String> findDistinctOrigenByDestino(String destino);

    @Query("SELECT i FROM Itinerario i " +
            "JOIN i.ruta r " +
            "JOIN i.disponibilidades d " +
            "WHERE r.origen = :origen " +
            "AND r.destino = :destino " +
            "AND d.fechaViaje = :fechaViaje")
    List<Itinerario> findByRutaAndFecha(
            @Param("origen") String origen,
            @Param("destino") String destino,
            @Param("fechaViaje") LocalDate fechaViaje
    );
}