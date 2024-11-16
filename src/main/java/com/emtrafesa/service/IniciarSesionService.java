package com.emtrafesa.service;

import com.emtrafesa.dto.IniciarSesionDTO;
import com.emtrafesa.dto.IniciarSesionRespuestaDTO;
import com.emtrafesa.model.entity.UserEmtraf;
import com.emtrafesa.repository.UserEmtrafRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IniciarSesionService {
    @Autowired
    private  UserEmtrafRepository userEmtrafRepository;
    @Autowired
   private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public IniciarSesionRespuestaDTO login(IniciarSesionDTO logindto) {
        Optional<UserEmtraf> userEm = userEmtrafRepository.findByCorreo(logindto.getCorreo());

        if (userEm.isPresent() && passwordEncoder.matches(logindto.getContrasena(), userEm.get().getContrasena())) {
            UserEmtraf userEmtraf = userEm.get();
            String token = jwtTokenProvider.generateToken(
                    userEmtraf.getId(),
                    userEmtraf.getNombre(),
                    userEmtraf.getApellidos(),
                    userEmtraf.getTipoUsuario().name()
            );
            return IniciarSesionRespuestaDTO.builder()
                    .id(userEmtraf.getId())
                    .nombre(userEmtraf.getNombre())
                    .apellidos(userEmtraf.getApellidos())
                    .token(token)
                    .tipoUsuario(userEmtraf.getTipoUsuario())
                    .build();
        } else {
            return IniciarSesionRespuestaDTO.builder()
                    .tipoUsuario(null)
                    .build();
        }
    }
}