package com.emtrafesa.mapper;

import com.emtrafesa.dto.BusRegistroDTO;
import com.emtrafesa.model.entity.Bus;
import org.springframework.stereotype.Component;

@Component
public class BusMapper {

    public Bus toEntity(BusRegistroDTO busRegistroDTO) {

        Bus bus = new Bus();
        bus.setPlaca(busRegistroDTO.getPlaca());
        bus.setModelo(busRegistroDTO.getModelo());
        bus.setServicio(busRegistroDTO.getTipoServicio());
        bus.setNumeroPisos(busRegistroDTO.getNumeroPisos());
        bus.setCantidadAsientosPorPiso(busRegistroDTO.getCantidadAsientosPorPiso());

        return bus;
    }
}
