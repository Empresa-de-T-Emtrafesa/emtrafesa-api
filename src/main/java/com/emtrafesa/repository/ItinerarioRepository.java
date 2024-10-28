package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.model.entity.Ruta;
import com.emtrafesa.model.enums.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {
    List<Itinerario> findByBusId(Long busId);
    Itinerario findTopByBusIdOrderByHoraLlegadaDesc(Long busId);
    boolean existsByBus(Bus bus);
    void deleteByRuta(Ruta ruta);
}