package com.emtrafesa.service;

import com.emtrafesa.dto.IniciarSesionDTO;
import com.emtrafesa.dto.IniciarSesionRespuestaDTO;
import com.emtrafesa.model.entity.UserEmtraf;
import com.emtrafesa.repository.UserEmtrafRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IniciarSesionService {

    private final UserEmtrafRepository userEmtrafRepository;

    public IniciarSesionService(UserEmtrafRepository userEmtrafRepository  ) {
        this.userEmtrafRepository = userEmtrafRepository;
    }

    public IniciarSesionRespuestaDTO login(IniciarSesionDTO logindto) {
        Optional<UserEmtraf> userEm = userEmtrafRepository.findByCorreoAndContrasena(logindto.getCorreo(), logindto.getContrasena());

        if (userEm.isPresent()) {
            UserEmtraf userEmtraf = userEm.get();
            return IniciarSesionRespuestaDTO.builder()
                    .mensaje("Inicio sesión exitoso.")
                    .tipoUsuario(userEmtraf.getTipoUsuario())
                    .build();
        } else {
            return IniciarSesionRespuestaDTO.builder()
                    .mensaje("Usuario no encontrado.")
                    .tipoUsuario(null)
                    .build();
        }
    }
}
