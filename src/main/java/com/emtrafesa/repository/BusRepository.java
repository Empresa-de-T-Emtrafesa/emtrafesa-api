package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByPlaca(String placa);

    boolean existsByPlaca(String placa);

}
