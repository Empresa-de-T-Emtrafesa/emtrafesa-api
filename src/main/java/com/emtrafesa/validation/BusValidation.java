package com.emtrafesa.validation;

import com.emtrafesa.dto.BusRegistroDTO;
import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.model.enums.EstadoBus;
import com.emtrafesa.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BusValidation {
    @Autowired
    private BusRepository busRepository;

    public void existePlaca (String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }
        if(busRepository.existsByPlaca(placa)) {
            throw new IllegalArgumentException("La placa " + placa + "ya se encuentra registrada.");
        }
    }

    public void validarEstadoBus(Bus bus) {
        if (bus.getEstadoBus() == null) {
            bus.setEstadoBus(EstadoBus.HABILITADO);
        }
        if (bus.getEstadoBus() == EstadoBus.INHABILITADO) {
            throw new IllegalArgumentException("El bus está inhabilitado y no puede ser seleccionado para un itinerario.");
        }
    }

    public void validarEntradasDeAsientos(BusRegistroDTO busRegistroDTO) {

        if (busRegistroDTO.getNumeroPisos() != busRegistroDTO.getCantidadAsientosPorPiso().size()) {
            throw new IllegalArgumentException("Debe especificar la cantidad de asientos para cada piso. Se esperaban "
                    + busRegistroDTO.getNumeroPisos() + " entradas, pero se recibieron " + busRegistroDTO.getCantidadAsientosPorPiso().size() + ".");
        }

        busRegistroDTO.getCantidadAsientosPorPiso().forEach((piso, cantidadAsientos) -> {
            if (piso <= 0 || piso > busRegistroDTO.getNumeroPisos() ) {
                throw new IllegalArgumentException("El número de piso especificado (" + piso + ") no es válido. Debe estar entre 1 y " + busRegistroDTO.getNumeroPisos() + ".");
            }

            if (cantidadAsientos <= 0) {
                throw new IllegalArgumentException("La cantidad de asientos debe ser mayor a cero.");
            }
        });
    }

    // Nuevo metodo para validar entradas de asientos cuando se modifica un bus
    public void validarEntradasDeAsientosModificar(Map<Integer, Integer> asientosPorPiso, Integer numeroPisos) {
        if (asientosPorPiso.size() != numeroPisos) {
            throw new IllegalArgumentException("Debe especificar la cantidad de asientos para cada piso. Se esperaban "
                    + numeroPisos + " entradas, pero se recibieron " + asientosPorPiso.size() + ".");
        }

        asientosPorPiso.forEach((piso, cantidadAsientos) -> {
            if (piso <= 0 || piso > numeroPisos) {
                throw new IllegalArgumentException("El número de piso especificado (" + piso + ") no es válido. Debe estar entre 1 y " + numeroPisos + ".");
            }

            if (cantidadAsientos <= 0) {
                throw new IllegalArgumentException("La cantidad de asientos debe ser mayor a cero.");
            }
        });
    }

}