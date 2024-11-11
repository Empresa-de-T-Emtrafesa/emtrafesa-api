package com.emtrafesa.mapper;

import com.emtrafesa.dto.DisponibilidadDTO;
import com.emtrafesa.dto.ItinerarioListarDTO;
import com.emtrafesa.dto.PrecioPorPisoDTO;
import com.emtrafesa.model.entity.DisponibilidadItinerario;
import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.model.entity.PrecioPorPiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ItinerarioListarMapper {
    private final RutaMapper rutaMapper;
    private final BusMapper busMapper;

    @Autowired
    public ItinerarioListarMapper(RutaMapper rutaMapper, BusMapper busMapper) {
        this.rutaMapper = rutaMapper;
        this.busMapper = busMapper;
    }

    public Itinerario toEntity(ItinerarioListarDTO itinerarioListarDTO) {
        Itinerario itinerario = new Itinerario();
        itinerario.setTieneEscalas(itinerarioListarDTO.getTieneEscalas());
        itinerario.setHoraSalida(itinerarioListarDTO.getHoraSalida());
        itinerario.setHoraLlegada(itinerarioListarDTO.getHoraLlegada());

        itinerario.setDisponibilidades(itinerarioListarDTO.getDisponibilidades().stream().map(d -> {
            DisponibilidadItinerario disponibilidad = new DisponibilidadItinerario();
            disponibilidad.setFechaViaje(d.getFechaViaje());
            disponibilidad.setItinerario(itinerario); // Vincula el itinerario
            return disponibilidad;
        }).collect(Collectors.toList()));

        itinerario.setPreciosPorPiso(itinerarioListarDTO.getPrecioPorPiso().stream().map(p -> {
            PrecioPorPiso precio = new PrecioPorPiso();
            precio.setPiso(p.getPiso());
            precio.setPrecio(p.getPrecio());
            precio.setItinerario(itinerario); // Vincula el itinerario
            return precio;
        }).collect(Collectors.toList()));

        return itinerario;
    }

    public ItinerarioListarDTO toDto(Itinerario itinerario) {
        ItinerarioListarDTO itinerarioListarDTO = new ItinerarioListarDTO();
        itinerarioListarDTO.setRutaId(rutaMapper.toDto(itinerario.getRuta()));
        itinerarioListarDTO.setBusId(busMapper.toDto(itinerario.getBus()));
        itinerarioListarDTO.setTieneEscalas(itinerario.getTieneEscalas());
        itinerarioListarDTO.setHoraSalida(itinerario.getHoraSalida());
        itinerarioListarDTO.setHoraLlegada(itinerario.getHoraLlegada());

        // Convertir disponibilidades
        itinerarioListarDTO.setDisponibilidades(itinerario.getDisponibilidades().stream()
                .map(d -> {
                    DisponibilidadDTO disponibilidadDTO = new DisponibilidadDTO();
                    disponibilidadDTO.setFechaViaje(d.getFechaViaje());
                    return disponibilidadDTO;
                })
                .collect(Collectors.toList()));

        // Convertir precios por piso
        itinerarioListarDTO.setPrecioPorPiso(itinerario.getPreciosPorPiso().stream()
                .map(p -> {
                    PrecioPorPisoDTO precioDTO = new PrecioPorPisoDTO();
                    precioDTO.setPiso(p.getPiso());
                    precioDTO.setPrecio(p.getPrecio());
                    return precioDTO;
                })
                .collect(Collectors.toList()));

        return itinerarioListarDTO;
    }
}