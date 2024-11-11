package com.emtrafesa.mapper;

import com.emtrafesa.dto.RutaDTO;
import com.emtrafesa.model.entity.Empleado;
import com.emtrafesa.model.entity.Ruta;
import com.emtrafesa.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RutaMapper {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Ruta toEntity(RutaDTO rutaDTO) {
        Ruta ruta = new Ruta();
        ruta.setOrigen(rutaDTO.getOrigen());
        ruta.setDestino(rutaDTO.getDestino());

        // Si el empleadoId no es nulo, busca y asigna el empleado correspondiente
        if (rutaDTO.getEmpleadoId() != null) {
            Empleado empleado = empleadoRepository.findById(rutaDTO.getEmpleadoId())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            ruta.setEmpleado(empleado);
        }

        return ruta;
    }

    public RutaDTO toDto(Ruta ruta) {
        RutaDTO rutaDTO = new RutaDTO();
        rutaDTO.setIdRuta(ruta.getId());
        rutaDTO.setOrigen(ruta.getOrigen());
        rutaDTO.setDestino(ruta.getDestino());

        // Asigna el ID del empleado si existe
        if (ruta.getEmpleado() != null) {
            rutaDTO.setEmpleadoId(ruta.getEmpleado().getId());
        }

        return rutaDTO;
    }
}
