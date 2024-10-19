package com.emtrafesa.service;

import com.emtrafesa.dto.ItinerarioDTO;
import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.model.entity.Ruta;
import com.emtrafesa.repository.BusRepository;
import com.emtrafesa.repository.ItinerarioRepository;
import com.emtrafesa.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RutaRepository rutaRepository;


    public List<Itinerario> filtrarItinerariosPorOrigenDestino(String origen, String destino, LocalDate fechaViaje) {
        return itinerarioRepository.findByRuta_OrigenAndRuta_DestinoAndFechaViaje(origen, destino, fechaViaje);
    }

    public Itinerario crearItinerario(ItinerarioDTO itinerarioDTO) {
        // Verificar que la ruta y el bus existan
        Optional<Ruta> ruta = rutaRepository.findById(itinerarioDTO.getRutaId());
        Optional<Bus> bus = busRepository.findById(itinerarioDTO.getBusId());

        if (ruta.isPresent() && bus.isPresent()) {
            Itinerario itinerario = new Itinerario();
            itinerario.setRuta(ruta.get());
            itinerario.setBus(bus.get());
            itinerario.setFechaViaje(itinerarioDTO.getFechaViaje());
            itinerario.setHoraSalida(itinerarioDTO.getHoraSalida());
            itinerario.setHoraLlegada(itinerarioDTO.getHoraLlegada());
            itinerario.setPreciosPorPiso(itinerarioDTO.getPreciosPorPiso());

            return itinerarioRepository.save(itinerario);
        } else {
            throw new RuntimeException("Ruta o Bus no encontrados");
        }
    }
}
