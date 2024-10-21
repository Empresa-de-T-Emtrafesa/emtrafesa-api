package com.emtrafesa.controller;


import com.emtrafesa.dto.ClienteRegistroDTO;
import com.emtrafesa.model.entity.Cliente;
import com.emtrafesa.service.ClienteRegistroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cliente")
@RestController
public class ClienteRegistroController {

    @Autowired
    private ClienteRegistroService clienteRegistroService;

    @PostMapping
    public ResponseEntity<String> registrarCliente(@Valid @RequestBody ClienteRegistroDTO clienteRegistroDTO) {
        try{
            clienteRegistroService.registrarCliente(clienteRegistroDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRegistroService.listaCliente();
    }
}
