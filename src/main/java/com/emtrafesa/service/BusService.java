package com.emtrafesa.service;

import com.emtrafesa.dto.BusRegistroDTO;
import com.emtrafesa.mapper.BusMapper;
import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusMapper busMapper;

    public void registrarBus (BusRegistroDTO busDTO){
        existePlaca(busDTO.getPlaca());
        validarEntradasDeAsientos(busDTO);
        Bus bus = busMapper.toEntity(busDTO);
        busRepository.save(bus);
    }

    public List<Bus> listarBus(){return busRepository.findAll();}

    private void existePlaca (String placa) {
        if(busRepository.existsByPlaca(placa)) {
            throw new IllegalArgumentException("La placa ya existe");
        }
    }

    public void validarEntradasDeAsientos(BusRegistroDTO busRegistroDTO) {
        if (busRegistroDTO.getNumeroPisos() != busRegistroDTO.getCantidadAsientosPorPiso().size()) {
            throw new IllegalArgumentException("Debe especificar la cantidad de asientos para cada piso. Se esperaban "
                    + busRegistroDTO.getNumeroPisos() + " entradas, pero se recibieron " + busRegistroDTO.getCantidadAsientosPorPiso().size() + ".");
        }

        busRegistroDTO.getCantidadAsientosPorPiso().forEach((piso, cantidadAsientos) -> {
            if (piso <= 0) {
                throw new IllegalArgumentException("El número de piso debe ser mayor a cero.");
            }

            if (cantidadAsientos <= 0) {
                throw new IllegalArgumentException("La cantidad de asientos debe ser mayor a cero.");
            }
        });
    }
}
