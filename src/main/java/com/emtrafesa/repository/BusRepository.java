package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByPlaca(String placa);

    boolean existsByPlaca(String placa);
    @Query("SELECT b.numeroPisos FROM Bus b WHERE b.id = :busId")
    Optional<Integer> findNumeroPisosById(@Param("busId") Long busId);

}