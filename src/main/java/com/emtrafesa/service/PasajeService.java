package com.emtrafesa.service;

import com.emtrafesa.dto.ResumenCompraDTO;
import com.emtrafesa.mapper.ResumenCompraMapper;
import com.emtrafesa.model.entity.Pasaje;
import com.emtrafesa.repository.PasajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasajeService {
    @Autowired
    private PasajeRepository pasajeRepository;

    @Autowired
    private ResumenCompraMapper resumenCompraMapper;

    public ResumenCompraDTO generarResumenDeCompra(Long pasajeId) {

        Pasaje pasaje = pasajeRepository.findById(pasajeId)
                .orElseThrow(() -> new RuntimeException("Pasaje no encontrado"));

        return resumenCompraMapper.toResumenCompraDTO(pasaje);
    }
}
