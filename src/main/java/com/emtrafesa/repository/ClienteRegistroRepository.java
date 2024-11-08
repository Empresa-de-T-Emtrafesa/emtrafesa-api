package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ClienteRegistroRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByNombreAndApellidos(String nombre, String apellidos);
    Optional<Cliente> findByNumeroDocumento(String numeroDocumento);
    Optional<Cliente> findByNumeroTelefono(String numeroTelefono);
}
