package com.emtrafesa.service;

import com.emtrafesa.dto.AsientoDTO;
import com.emtrafesa.dto.BusMapDTO;
import com.emtrafesa.exception.ResourceNotFoundException;
import com.emtrafesa.mapper.AsientoMapper;
import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.model.entity.Asiento;
import com.emtrafesa.repository.AsientoRepository;
import com.emtrafesa.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private AsientoMapper asientoMapper;

    public BusMapDTO getMapaAsientos(Long busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus no encontrado con id: " + busId));

        List<Asiento> asientos = asientoRepository.findByBusId(busId);
        List<AsientoDTO> asientosDTO = asientos.stream()
                .map(asientoMapper::toDTO)
                .collect(Collectors.toList());

        BusMapDTO mapa = new BusMapDTO();
        mapa.setBusId(bus.getId());
        mapa.setPlaca(bus.getPlaca());
        mapa.setAsientos(asientosDTO);

        return mapa;
    }

    // Puedes agregar más métodos según las necesidades futuras
}
