package com.emtrafesa.service;

import com.emtrafesa.dto.PasajeroRegistroDTO;
import com.emtrafesa.mapper.PasajeroRegistroMapper;
import com.emtrafesa.model.entity.Pasajero;
import com.emtrafesa.model.enums.TipoDocumento;
import com.emtrafesa.repository.PasajeroRegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasajeroRegistroService {

    @Autowired
    private PasajeroRegistroRepository pasajeroRegistroRepository;

    @Autowired
    private PasajeroRegistroMapper pasajeroRegistroMapper;

    public PasajeroRegistroDTO registrarPasajero(PasajeroRegistroDTO pasajeroRegistroDTO) {
        validarNombreyApellidos(pasajeroRegistroDTO.getNombre(), pasajeroRegistroDTO.getApellidos());
        validarDocumento(pasajeroRegistroDTO.getNumeroDocumento());
        validarNumeroDocumento(pasajeroRegistroDTO.getTipoDocumento(), pasajeroRegistroDTO.getNumeroDocumento());
        Pasajero pasajero = pasajeroRegistroMapper.toEntity(pasajeroRegistroDTO);
        pasajeroRegistroRepository.save(pasajero);
        return pasajeroRegistroMapper.toDTO(pasajero);
    }

    public List<Pasajero> listaPasajero(){
        return pasajeroRegistroRepository.findAll();
    }

    private void validarNombreyApellidos(String nombre, String apellidos) {
        if(pasajeroRegistroRepository.findByNombreAndApellidos(nombre,apellidos).isPresent()){
            throw new IllegalArgumentException("El nombre y apellido ya están registrados");
        }
    }

    private void validarDocumento(String numeroDocumento) {
        if(pasajeroRegistroRepository.findByNumeroDocumento(numeroDocumento).isPresent()){
            throw new IllegalArgumentException("El número de documento ya está registrado.");
        }
    }

    private void validarNumeroDocumento(TipoDocumento tipoDocumento, String numeroDocumento) {
        if(tipoDocumento == TipoDocumento.DNI && numeroDocumento.length() !=8) {
            throw new IllegalArgumentException("El DNI debe tener 8 dígitos");
        }else if(tipoDocumento == TipoDocumento.CARNET_DE_EXTRANJERIA && numeroDocumento.length() >20) {
            throw new IllegalArgumentException("El Carnet de Extranjería no puede exceder los 20 dígitos");
        }
    }
}
