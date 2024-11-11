package com.emtrafesa.validation;

import com.emtrafesa.dto.ItinerarioDTO;
import com.emtrafesa.dto.ItinerarioListarDTO;
import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.model.entity.Itinerario;
import com.emtrafesa.repository.ItinerarioRepository;
import com.emtrafesa.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ItinerarioValidation {
    @Autowired
    private ItinerarioRepository itinerarioRepository;
    @Autowired
    private RutaRepository rutaRepository;

    // Validar que las fechas del itinerario sean válidas
    public void validarFechas(ItinerarioDTO itinerarioDTO) {
        if (itinerarioDTO.getDisponibilidades() == null || itinerarioDTO.getDisponibilidades().isEmpty()){
            throw new IllegalArgumentException("El itinerario debe tener al menos una fecha seleccionada");
        }
        // Verificar que las fechas no sean anteriores al día actual
        itinerarioDTO.getDisponibilidades().forEach(disponibilidad ->{
            if(disponibilidad.getFechaViaje().isBefore(LocalDate.now())){
                throw new IllegalArgumentException("Se debe seleccionar la fecha actual o posteriores.");
            }
        });
    }

    // Validar que la hora de salida sea antes que la hora de llegada
    public void validarHoras(ItinerarioDTO itinerarioDTO) {
        if(itinerarioDTO.getHoraSalida().isAfter(itinerarioDTO.getHoraLlegada())
                || itinerarioDTO.getHoraSalida().equals(itinerarioDTO.getHoraLlegada())){
            throw new IllegalArgumentException("La hora de salida debe ser anterior a la hora de llegada y no pueden ser iguales.");
        }
    }

    // Validar que los precios no sean negativos o cero
    public void validarPreciosPorPiso(ItinerarioDTO itinerarioDTO, Bus bus) {
        // Verificar que el número de pisos no sea nulo
        if (bus.getNumeroPisos() == null) {
            throw new IllegalArgumentException("El número de pisos del bus no puede ser nulo.");
        }
        // Verificar que el número de precios por piso sea igual al número de pisos del bus
        if (itinerarioDTO.getPrecioPorPiso().size() != bus.getNumeroPisos()) {
            throw new IllegalArgumentException("Debe especificar un precio para cada piso del bus. Se esperaban "
                    + bus.getNumeroPisos() + " entradas, pero se recibieron "
                    + itinerarioDTO.getPrecioPorPiso().size() + ".");
        }

        // Validar cada entrada de precio por piso
        itinerarioDTO.getPrecioPorPiso().forEach(precioDTO -> {
            int piso = precioDTO.getPiso();
            double precio = precioDTO.getPrecio();

            // Verificar que el piso especificado esté dentro del rango permitido
            if (piso <= 0 || piso > bus.getNumeroPisos()) {
                throw new IllegalArgumentException("El número de piso " + piso + " no es válido para este bus. Debe estar entre 1 y " + bus.getNumeroPisos() + ".");
            }

            // Verificar que el precio sea positivo
            if (precio <= 0) {
                throw new IllegalArgumentException("El precio para el piso " + piso + " debe ser mayor a cero.");
            }
        });
    }


    // Validar que el origen y destino de la ruta sean diferentes
    public void validarOrigenDestino(Long rutaId) {
        rutaRepository.findById(rutaId).ifPresent(ruta -> {
            if (ruta.getOrigen().equalsIgnoreCase(ruta.getDestino())) {
                throw new IllegalArgumentException("El origen y el destino no pueden ser iguales.");
            }
        });
    }

    public void disponiblidadBus (ItinerarioDTO itinerarioDTO, Long busId) {
        Itinerario ultimoItinerario = itinerarioRepository.findTopByBusIdOrderByHoraLlegadaDesc(busId);

        if(ultimoItinerario != null){
            validarHorarios(ultimoItinerario, itinerarioDTO);

            if(!verificarDestinoOrigen(ultimoItinerario, itinerarioDTO)){
                throw new IllegalArgumentException("El bus no se encuentra en ese origen, se encuentra en otro lugar.");
            }

        }

    }

    public void validarHorarios(Itinerario itinerario, ItinerarioDTO itinerarioDTO) {
        LocalTime horaLlegadaExistente = itinerario.getHoraLlegada()
                .plusMinutes(30);
        LocalTime nuevaHoraSalida = itinerarioDTO.getHoraSalida();

        if(! nuevaHoraSalida.isAfter(horaLlegadaExistente)){
            throw new IllegalArgumentException("El bus ya está reservado para el horario seleccionado en la fecha " );
        }
    }

    private boolean verificarDestinoOrigen(Itinerario itinerarioExistente, ItinerarioDTO itinerarioDTO) {
        String destinoAnterior = itinerarioExistente.getRuta().getDestino();
        String origenNuevo = rutaRepository.findById(itinerarioDTO.getRutaId())
                .orElseThrow(() -> new IllegalArgumentException("La ruta no existe."))
                .getOrigen();
        return destinoAnterior.equals(origenNuevo);
    }
}