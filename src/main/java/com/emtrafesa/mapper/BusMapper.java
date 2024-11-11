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
        bus.setEstadoBus(busRegistroDTO.getEstadoBus());
        return bus;
    }

    public BusRegistroDTO toDto(Bus bus) {
        BusRegistroDTO busDTO = new BusRegistroDTO();
        busDTO.setIdBus(bus.getId());
        busDTO.setPlaca(bus.getPlaca());
        busDTO.setModelo(bus.getModelo());
        busDTO.setTipoServicio(bus.getServicio());
        busDTO.setNumeroPisos(bus.getNumeroPisos());
        busDTO.setCantidadAsientosPorPiso(bus.getCantidadAsientosPorPiso());
        busDTO.setEstadoBus(bus.getEstadoBus());
        return busDTO;
    }
}
