package com.emtrafesa.mapper;

import com.emtrafesa.dto.ClienteRegistroDTO;
import com.emtrafesa.model.entity.Cliente;
import com.emtrafesa.model.entity.UserEmtraf;
import org.springframework.stereotype.Component;

@Component
public class ClienteRegistroMapper {

    public Cliente toEntity(ClienteRegistroDTO clienteDTO, UserEmtraf userEmtraf){
        Cliente cliente = new Cliente();
        userEmtraf.setNombre(clienteDTO.getNombre());
        userEmtraf.setApellidos(clienteDTO.getApellidos());
        cliente.setUserEmtraf(userEmtraf);
        cliente.setTipoTelefono(clienteDTO.getTipoTelefono());
        cliente.setNumeroTelefono(clienteDTO.getNumeroTelefono());
        cliente.setTipoDocumento(clienteDTO.getTipoDocumento());
        cliente.setNumeroDocumento(clienteDTO.getNumeroDocumento());
        return cliente;
    }
<<<<<<< Updated upstream
=======

    public ClienteRegistroDTO toDto(Cliente cliente) {
        ClienteRegistroDTO dto = new ClienteRegistroDTO();
        dto.setId(cliente.getId());  // Asegúrate de que el ID del cliente se incluya en el DTO de salida
        dto.setNombre(cliente.getUserEmtraf().getNombre());
        dto.setApellidos(cliente.getUserEmtraf().getApellidos());
        dto.setTipoUsuario(cliente.getUserEmtraf().getTipoUsuario());
        dto.setCorreo(cliente.getUserEmtraf().getCorreo());
        dto.setTipoDocumento(cliente.getTipoDocumento());
        dto.setNumeroDocumento(cliente.getNumeroDocumento());
        dto.setTipoTelefono(cliente.getTipoTelefono());
        dto.setNumeroTelefono(cliente.getNumeroTelefono());
        return dto;
    }
>>>>>>> Stashed changes
}