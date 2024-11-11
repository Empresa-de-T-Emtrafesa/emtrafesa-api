package com.emtrafesa.mapper;

import com.emtrafesa.dto.DisponibilidadDTO;
import com.emtrafesa.dto.ItinerarioDTO;
import com.emtrafesa.dto.PrecioPorPisoDTO;
import com.emtrafesa.model.entity.DisponibilidadItinerario;
import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.model.entity.PrecioPorPiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItinerarioMapper {
    private final RutaMapper rutaMapper;
    private final BusMapper busMapper;

    @Autowired
    public ItinerarioMapper(RutaMapper rutaMapper, BusMapper busMapper) {
        this.rutaMapper = rutaMapper;
        this.busMapper = busMapper;
    }

    public Itinerario toEntity(ItinerarioDTO itinerarioDTO) {
        Itinerario itinerario = new Itinerario();
        itinerario.setTieneEscalas(itinerarioDTO.getTieneEscalas());
        itinerario.setHoraSalida(itinerarioDTO.getHoraSalida());
        itinerario.setHoraLlegada(itinerarioDTO.getHoraLlegada());

        itinerario.setDisponibilidades(itinerarioDTO.getDisponibilidades().stream().map(d -> {
            DisponibilidadItinerario disponibilidad = new DisponibilidadItinerario();
            disponibilidad.setFechaViaje(d.getFechaViaje());
            disponibilidad.setItinerario(itinerario); // Vincula el itinerario
            return disponibilidad;
        }).collect(Collectors.toList()));

        itinerario.setPreciosPorPiso(itinerarioDTO.getPrecioPorPiso().stream().map(p -> {
            PrecioPorPiso precio = new PrecioPorPiso();
            precio.setPiso(p.getPiso());
            precio.setPrecio(p.getPrecio());
            precio.setItinerario(itinerario); // Vincula el itinerario
            return precio;
        }).collect(Collectors.toList()));

        return itinerario;
    }

    public ItinerarioDTO toDto(Itinerario itinerario) {
        ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
        itinerarioDTO.setRutaId(rutaMapper.toDto(itinerario.getRuta()));
        itinerarioDTO.setBusId(busMapper.toDto(itinerario.getBus()));
        itinerarioDTO.setTieneEscalas(itinerario.getTieneEscalas());
        itinerarioDTO.setHoraSalida(itinerario.getHoraSalida());
        itinerarioDTO.setHoraLlegada(itinerario.getHoraLlegada());

        // Convertir disponibilidades
        itinerarioDTO.setDisponibilidades(itinerario.getDisponibilidades().stream()
                .map(d -> {
                    DisponibilidadDTO disponibilidadDTO = new DisponibilidadDTO();
                    disponibilidadDTO.setFechaViaje(d.getFechaViaje());
                    return disponibilidadDTO;
                })
                .collect(Collectors.toList()));

        // Convertir precios por piso
        itinerarioDTO.setPrecioPorPiso(itinerario.getPreciosPorPiso().stream()
                .map(p -> {
                    PrecioPorPisoDTO precioDTO = new PrecioPorPisoDTO();
                    precioDTO.setPiso(p.getPiso());
                    precioDTO.setPrecio(p.getPrecio());
                    return precioDTO;
                })
                .collect(Collectors.toList()));

        return itinerarioDTO;
    }
}