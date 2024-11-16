package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRegistroRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.userEmtraf.nombre = :nombre AND c.userEmtraf.apellidos = :apellidos")
    Optional<Cliente> findByNombreAndApellidos(@Param("nombre") String nombre, @Param("apellidos") String apellidos);

    Optional<Cliente> findByNumeroTelefono(String numeroTelefono);

    Optional<Cliente> findByNumeroDocumento(String numeroDocumento);
}
