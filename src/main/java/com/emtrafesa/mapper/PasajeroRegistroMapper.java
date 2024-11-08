package com.emtrafesa.mapper;

import com.emtrafesa.dto.PasajeroRegistroDTO;
import com.emtrafesa.model.entity.Pasajero;
import org.springframework.stereotype.Component;

@Component
public class PasajeroRegistroMapper {
    public Pasajero toEntity(PasajeroRegistroDTO pasajeroDTO){

    Pasajero pasajero = new Pasajero();
    pasajero.setNombre(pasajeroDTO.getNombre());
    pasajero.setApellidos(pasajeroDTO.getApellidos());
    pasajero.setSexo(pasajeroDTO.getSexo());
    pasajero.setTipoDocumento(pasajeroDTO.getTipoDocumento());
    pasajero.setNumeroDocumento(pasajeroDTO.getNumeroDocumento());
    pasajero.setFechaNacimiento(pasajeroDTO.getFechaNacimiento());

    return pasajero;
    }
}
