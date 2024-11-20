package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {

    Optional<Ruta> findByOrigenAndDestino(String origen, String destino);

    @Query("SELECT DISTINCT r.origen FROM Ruta r")
    List<String> findDistinctOrigenes();

    @Query("SELECT DISTINCT r.destino FROM Ruta r")
    List<String> findDistinctDestinos();

    @Query("SELECT DISTINCT r.destino FROM Ruta r WHERE r.origen = :origen")
    List<String> findDistinctDestinosByOrigen(@Param("origen") String origen);
}
