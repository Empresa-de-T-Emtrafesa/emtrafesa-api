package com.emtrafesa.mapper;

import com.emtrafesa.dto.IniciarSesionDTO;
import com.emtrafesa.model.entity.UserEmtraf;
import org.springframework.stereotype.Component;

@Component
public class IniciarSesionMapper {

    public UserEmtraf toEntity(IniciarSesionDTO logindto){
        UserEmtraf user = new UserEmtraf();
        user.setCorreo(logindto.getCorreo());
        user.setContrasena(logindto.getContrasena());
        return user;
    }
}