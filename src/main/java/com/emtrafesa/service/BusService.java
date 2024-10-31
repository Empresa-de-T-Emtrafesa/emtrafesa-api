package com.emtrafesa.service;

import com.emtrafesa.dto.BusRegistroDTO;
import com.emtrafesa.mapper.BusMapper;
import com.emtrafesa.model.entity.Bus;
import com.emtrafesa.model.enums.EstadoBus;
import com.emtrafesa.repository.BusRepository;
import com.emtrafesa.repository.ItinerarioRepository;
import com.emtrafesa.validation.BusValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private BusMapper busMapper;

    @Autowired
    private BusValidation busValidation;

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    public void registrarBus (BusRegistroDTO busDTO){
        busValidation.validarEntradasDeAsientos(busDTO);
        busValidation.validarEntradasDeAsientos(busDTO);

        Bus bus = busMapper.toEntity(busDTO);
        busRepository.save(bus);
    }

    public void verificarEstadoBus(Long busId){
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el bus"));
        busValidation.validarEstadoBus(bus);
    }

    //CAMBIA 1 O MÁS DATOS DEL BUS [A ELECCIÓN]
    public Bus modificarBus (Long id, BusRegistroDTO busDTO){
        Bus busExistente = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus no encontrado"));

        // Verificar si el bus está asociado a algún itinerario
        boolean estaAsociadoAItinerario = itinerarioRepository.existsByBus(busExistente);
        if (estaAsociadoAItinerario) {
            throw new IllegalArgumentException("El bus no puede ser modificado porque está asociado a un itinerario.");
        }

        // Validar y actualizar campos en el bus existente solo si están presentes en el DTO
        Optional.ofNullable(busDTO.getPlaca()).ifPresent(placa -> {
            busValidation.existePlaca(placa);
            busExistente.setPlaca(placa);
        });

        Optional.ofNullable(busDTO.getModelo()).ifPresent(busExistente::setModelo);
        Optional.ofNullable(busDTO.getEstadoBus()).ifPresent(busExistente::setEstadoBus);
        Optional.ofNullable(busDTO.getNumeroPisos()).ifPresent(pisos -> {
            if (pisos > 0) busExistente.setNumeroPisos(pisos);
        });

        Optional.ofNullable(busDTO.getCantidadAsientosPorPiso()).ifPresent(asientos -> {
            busValidation.validarEntradasDeAsientosModificar(asientos, busExistente.getNumeroPisos());
            busExistente.setCantidadAsientosPorPiso(asientos);
        });

        return busRepository.save(busExistente);
    }

    public List<Bus> listarBus(){return busRepository.findAll();}
}