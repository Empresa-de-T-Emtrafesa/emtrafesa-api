package com.emtrafesa.controller;

import com.emtrafesa.dto.IniciarSesionDTO;
import com.emtrafesa.dto.IniciarSesionRespuestaDTO;
import com.emtrafesa.service.IniciarSesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class IniciarSesionController {

    private final IniciarSesionService iniciarSesionService;

    @Autowired
    public IniciarSesionController(IniciarSesionService iniciarSesionService) {
        this.iniciarSesionService = iniciarSesionService;
    }

    @PostMapping
    public IniciarSesionRespuestaDTO login (@RequestBody IniciarSesionDTO iniciarSesionDTO) {
        return iniciarSesionService.login(iniciarSesionDTO);
    }
}