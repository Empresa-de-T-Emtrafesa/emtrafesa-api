package com.emtrafesa.service;

import com.emtrafesa.dto.ItinerarioDTO;
import com.emtrafesa.mapper.ItinerarioMapper;
import com.emtrafesa.model.entity.*;
import com.emtrafesa.repository.BusRepository;
import com.emtrafesa.repository.DisponibilidadItinerarioRepository;
import com.emtrafesa.repository.ItinerarioRepository;
import com.emtrafesa.repository.RutaRepository;
import com.emtrafesa.validation.BusValidation;
import com.emtrafesa.validation.ItinerarioValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private ItinerarioValidation itinerarioValidation;

    @Autowired
    private ItinerarioMapper itinerarioMapper;

    @Autowired
    private BusService busService;
    @Autowired
    private DisponibilidadItinerarioRepository disponibilidadRepository;

    // Obtener destinos válidos para un origen específico
    public List<String> getDestinosDisponibles(String origen) {
        return itinerarioRepository.findDistinctDestinosByOrigen(origen);
    }

    public List<String> getOrigenesDisponibles(String destino){
        return itinerarioRepository.findDistinctOrigenByDestino(destino);
    }

    // Obtener fechas válidas para un origen y destino específico
    public List<LocalDate> getFechasDisponibles(String origen, String destino) {
        return disponibilidadRepository.findFechasDisponiblesByOrigenDestino(origen, destino);
    }

    public List<ItinerarioDTO> filtrarItinerarios(String origen, String destino, LocalDate fechaViaje) {
        List<Itinerario> itinerarios = itinerarioRepository.findByRutaAndFecha(origen, destino, fechaViaje);
        return itinerarios.stream().map(itinerario -> {
            ItinerarioDTO dto = itinerarioMapper.toDto(itinerario);

            // Filtra solo la disponibilidad de la fecha específica en el DTO
            dto.setDisponibilidades(dto.getDisponibilidades().stream()
                    .filter(d -> d.getFechaViaje().equals(fechaViaje))
                    .collect(Collectors.toList()));

            return dto;
        }).collect(Collectors.toList());
    }

    private void validarItinerario(ItinerarioDTO itinerarioDTO, Bus bus) {
        itinerarioValidation.validarFechas(itinerarioDTO);
        itinerarioValidation.validarHoras(itinerarioDTO);
        itinerarioValidation.validarPreciosPorPiso(itinerarioDTO, bus);
        itinerarioValidation.validarOrigenDestino(itinerarioDTO.getRutaId().getIdRuta());
        itinerarioValidation.disponiblidadBus(itinerarioDTO, itinerarioDTO.getBusId().getIdBus());
    }

    public Itinerario crearItinerario(ItinerarioDTO itinerarioDTO) {

        // Buscar la ruta y el bus
        Bus bus = busRepository.findById(itinerarioDTO.getBusId().getIdBus())
                .orElseThrow(() -> new RuntimeException("Bus no encontrado"));

        // Validar que el bus esté habilitado y que el itinerario sea válido
        busService.verificarEstadoBus(itinerarioDTO.getBusId().getIdBus());
        validarItinerario(itinerarioDTO, bus);

        // Buscar la ruta asociada
        Ruta ruta = rutaRepository.findById(itinerarioDTO.getRutaId().getIdRuta())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        // Mapear y guardar el itinerario
        Itinerario itinerario = itinerarioMapper.toEntity(itinerarioDTO);
        itinerario.setRuta(ruta);
        itinerario.setBus(bus);

        return itinerarioRepository.save(itinerario);
    }


    public Itinerario modificarItinerario (Long id, ItinerarioDTO itinerarioDTO) {
        Itinerario itinerarioExistente = itinerarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Itinerario no encontrado"));

        // Verificar si el bus está habilitado si se proporciona un nuevo bus
        if (itinerarioDTO.getBusId() != null) {
            Bus bus = busRepository.findById(itinerarioDTO.getBusId().getIdBus())
                    .orElseThrow(() -> new RuntimeException("Bus no encontrado"));
            busService.verificarEstadoBus(bus.getId());
            itinerarioExistente.setBus(bus);
            validarItinerario(itinerarioDTO,bus);
        }
        if (itinerarioDTO.getRutaId()!= null) {
            Ruta ruta = rutaRepository.findById(itinerarioDTO.getRutaId().getIdRuta())
                    .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
            itinerarioExistente.setRuta(ruta);
        }

        if (itinerarioDTO.getTieneEscalas() != null) {
            itinerarioExistente.setTieneEscalas(itinerarioDTO.getTieneEscalas());
        }

        if(itinerarioDTO.getHoraSalida()!= null) {
            itinerarioExistente.setHoraSalida(itinerarioDTO.getHoraSalida());
        }

        if(itinerarioDTO.getHoraLlegada()!= null) {
            itinerarioExistente.setHoraLlegada(itinerarioDTO.getHoraLlegada());
        }

        // Actualizar las disponibilidades (fechas de viaje) si se proporcionan
        if (itinerarioDTO.getDisponibilidades() != null) {
            itinerarioExistente.getDisponibilidades().clear();
            itinerarioDTO.getDisponibilidades().forEach(disponibilidadDTO -> {
                DisponibilidadItinerario disponibilidad = new DisponibilidadItinerario();
                disponibilidad.setFechaViaje(disponibilidadDTO.getFechaViaje());
                disponibilidad.setItinerario(itinerarioExistente);
                itinerarioExistente.getDisponibilidades().add(disponibilidad);
            });
        }

        // Actualizar los precios por piso si se proporcionan
        if (itinerarioDTO.getPrecioPorPiso() != null) {
            itinerarioExistente.getPreciosPorPiso().clear();
            itinerarioDTO.getPrecioPorPiso().forEach(precioDTO -> {
                PrecioPorPiso nuevoPrecio = new PrecioPorPiso();
                nuevoPrecio.setPiso(precioDTO.getPiso());
                nuevoPrecio.setPrecio(precioDTO.getPrecio());
                nuevoPrecio.setItinerario(itinerarioExistente);
                itinerarioExistente.getPreciosPorPiso().add(nuevoPrecio);
            });
        }
        return itinerarioRepository.save(itinerarioExistente);
    }

    public void eliminarItinerario (Long id){
        Itinerario itinerario = itinerarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Itinerario no encontrado"));
        itinerarioRepository.delete(itinerario);
    }

    public List<ItinerarioDTO> listarItinerario() {
        return itinerarioRepository.findAll().stream()
                .map(itinerarioMapper::toDto)
                .toList();
    }

}