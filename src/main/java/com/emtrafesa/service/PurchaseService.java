package com.emtrafesa.service;

import com.emtrafesa.dto.PagoCreateUpdateDTO;
import com.emtrafesa.dto.PagoDTO;
import com.emtrafesa.dto.PagoReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {
    // Crear un nuevo pago a partir de un DTO de creación
    PagoDTO createPago(PagoCreateUpdateDTO pagoCreateUpdateDTO);

    // Obtener el historial de pagos por ID de cliente
    List<PagoDTO> getPagoHistoryByClienteId(Long clienteId);

    // Obtener todos los pagos
    List<PagoDTO> getAllPagos();

    // Confirmar un pago específico por ID
    PagoDTO confirmPago(Long pagoId);

    // Obtener un pago específico por ID
    PagoDTO getPagoById(Long id);

    // Generar un reporte de pagos basado en fecha u otros criterios
    List<PagoReportDTO> getPagoReportByDate(String date);
}
