package com.emtrafesa.mapper;

import com.emtrafesa.dto.ItinerarioDTO;
import com.emtrafesa.model.entity.Itinerario;
import org.springframework.stereotype.Component;

@Component
public class ItinerarioMapper {

    public Itinerario toEntity(ItinerarioDTO itinerarioDTO) {
        Itinerario itinerario = new Itinerario();
        itinerario.setDisponibilidades(itinerarioDTO.getDisponiblidades());
        itinerario.setTieneEscalas(itinerarioDTO.getTieneEscalas());
        itinerario.setHoraSalida(itinerarioDTO.getHoraSalida());
        itinerario.setHoraLlegada(itinerarioDTO.getHoraLlegada());
        itinerario.setPreciosPorPiso(itinerarioDTO.getPreciosPorPiso());

        return itinerario;
    }
}
