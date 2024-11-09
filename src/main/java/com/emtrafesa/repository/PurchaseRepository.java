package com.emtrafesa.repository;

import com.emtrafesa.model.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Pago, Integer> {


}
