package com.emtrafesa.mapper;

import com.emtrafesa.dto.PasajeroRegistroDTO;
import com.emtrafesa.model.entity.Pasajero;
import org.springframework.stereotype.Component;

@Component
public class PasajeroRegistroMapper {
    public Pasajero toEntity(PasajeroRegistroDTO dto) {
        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(dto.getNombre());
        pasajero.setApellidos(dto.getApellidos());
        pasajero.setTipoDocumento(dto.getTipoDocumento());
        pasajero.setNumeroDocumento(dto.getNumeroDocumento());
        pasajero.setSexo(dto.getSexo());
        pasajero.setFechaNacimiento(dto.getFechaNacimiento());
        return pasajero;
    }

    public PasajeroRegistroDTO toDTO(Pasajero pasajero) {
        PasajeroRegistroDTO dto = new PasajeroRegistroDTO();
        dto.setId(pasajero.getId());
        dto.setNombre(pasajero.getNombre());
        dto.setApellidos(pasajero.getApellidos());
        dto.setTipoDocumento(pasajero.getTipoDocumento());
        dto.setNumeroDocumento(pasajero.getNumeroDocumento());
        dto.setSexo(pasajero.getSexo());
        dto.setFechaNacimiento(pasajero.getFechaNacimiento());
        return dto;
    }
}