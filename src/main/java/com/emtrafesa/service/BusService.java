package com.emtrafesa.service;

import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus saveBus(Bus bus) {
        validateNumeroPisos(bus);
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();  // Método para obtener todos los buses
    }

    private void validateNumeroPisos(Bus bus) {
        if (bus.getCantidadAsientosPorPiso().size() != bus.getNumeroPisos()) {
            throw new IllegalArgumentException("El número de pisos no coincide con la cantidad de niveles de asientos proporcionados.");
        }

        for (Integer cantidadAsientos : bus.getCantidadAsientosPorPiso().values()) {
            if (cantidadAsientos <= 0) {
                throw new IllegalArgumentException("La cantidad de asientos por piso debe ser mayor a 0.");
            }
        }
    }
}
