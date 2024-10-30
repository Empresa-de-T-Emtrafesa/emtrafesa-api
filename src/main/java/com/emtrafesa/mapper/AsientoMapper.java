package com.emtrafesa.mapper;

import com.emtrafesa.dto.AsientoDTO;
import com.emtrafesa.model.entity.Asiento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsientoMapper {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Convierte una entidad Asiento a AsientoDTO.
     *
     * @param asiento Entidad Asiento.
     * @return AsientoDTO.
     */
    public AsientoDTO toDTO(Asiento asiento) {
        AsientoDTO dto = modelMapper.map(asiento, AsientoDTO.class);
        dto.setBusId(asiento.getBus().getId());
        return dto;
    }

    /**
     * Convierte un AsientoDTO a una entidad Asiento.
     *
     * @param dto AsientoDTO.
     * @return Entidad Asiento.
     */
    public Asiento toEntity(AsientoDTO dto) {
        Asiento asiento = modelMapper.map(dto, Asiento.class);
        // Aquí podrías manejar la asignación de Bus si es necesario
        return asiento;
    }
}
