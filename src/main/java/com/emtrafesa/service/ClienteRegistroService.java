package com.emtrafesa.service;

import ch.qos.logback.core.net.server.Client;
import com.emtrafesa.dto.ClienteRegistroDTO;
import com.emtrafesa.mapper.ClienteRegistroMapper;
import com.emtrafesa.model.entity.Cliente;
import com.emtrafesa.model.entity.UserEmtraf;
import com.emtrafesa.model.enums.TipoDocumento;
import com.emtrafesa.model.enums.TipoUsuario;
import com.emtrafesa.repository.ClienteRegistroRepository;
import com.emtrafesa.repository.UserEmtrafRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteRegistroService {

    @Autowired
    private ClienteRegistroMapper clienteRegistroMapper;

    @Autowired
    private UserEmtrafRepository userEmtrafRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClienteRegistroRepository clienteRegistroRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarCliente(ClienteRegistroDTO clienteRegistroDTO) {
        validarCorreo(clienteRegistroDTO.getCorreo());
        validarNombreyApellidos(clienteRegistroDTO.getNombre(), clienteRegistroDTO.getApellidos());
        validarTelefono(clienteRegistroDTO.getNumeroTelefono());
        validarDocumento(clienteRegistroDTO.getNumeroDocumento());
        validarNumeroDocumento(clienteRegistroDTO.getTipoDocumento(), clienteRegistroDTO.getNumeroDocumento());

        UserEmtraf userEmtraf = new UserEmtraf();
        userEmtraf.setCorreo(clienteRegistroDTO.getCorreo());

        // Encriptar la contraseña y verificar
        String encodedPassword = passwordEncoder.encode(clienteRegistroDTO.getContrasena());
        userEmtraf.setContrasena(encodedPassword);

        userEmtraf.setTipoUsuario(TipoUsuario.CLIENTE);
        userEmtrafRepository.save(userEmtraf);

        Cliente cliente = clienteRegistroMapper.toEntity(clienteRegistroDTO, userEmtraf);

        clienteRegistroRepository.save(cliente);

        emailService.sendWelcomeEmail(clienteRegistroDTO.getCorreo());
    }

    public List<Cliente> listaCliente(){
        return clienteRegistroRepository.findAll();
    }

   private void validarTelefono(String numeroTelefono) {
       if(clienteRegistroRepository.findByNumeroTelefono(numeroTelefono).isPresent()) {
            throw new IllegalArgumentException("El número de teléfono ya está registrado");
       }
   }

   private void validarNombreyApellidos(String nombre, String apellidos) {
       if(clienteRegistroRepository.findByNombreAndApellidos(nombre, apellidos).isPresent()) {
           throw new IllegalArgumentException("El nombre y apellido ya están registrados");
       }
   }

   private void validarDocumento(String numeroDocumento) {
       if(clienteRegistroRepository.findByNumeroDocumento(numeroDocumento).isPresent()) {
           throw new IllegalArgumentException("El número de documento ya está registrado.");
       }
   }

   private void validarCorreo(String correo) {
       if(userEmtrafRepository.findByCorreo(correo).isPresent()) {
           throw new IllegalArgumentException("El correo ya se encuentra en uso.");
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
