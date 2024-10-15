package com.emtrafesa.mapper;

import com.emtrafesa.dto.ClienteRegistroDTO;
import com.emtrafesa.model.entity.Cliente;
import com.emtrafesa.model.entity.UserEmtraf;
import org.springframework.stereotype.Component;

@Component
public class ClienteRegistroMapper {

    public Cliente toEntity(ClienteRegistroDTO clienteDTO, UserEmtraf userEmtraf){
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setUserEmtraf(userEmtraf);
        cliente.setTipoTelefono(clienteDTO.getTipoTelefono());
        cliente.setNumeroTelefono(clienteDTO.getNumeroTelefono());
        cliente.setTipoDocumento(clienteDTO.getTipoDocumento());
        cliente.setNumeroDocumento(clienteDTO.getNumeroDocumento());
        return cliente;
    }
}
