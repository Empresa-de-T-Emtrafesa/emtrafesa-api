package com.emtrafesa.service.impl;

import com.emtrafesa.dto.PagoCreateUpdateDTO;
import com.emtrafesa.dto.PagoDTO;
import com.emtrafesa.dto.PagoReportDTO;
import com.emtrafesa.repository.ItinerarioRepository;
import com.emtrafesa.repository.PurchaseRepository;
import com.emtrafesa.repository.UserEmtrafRepository;
import com.emtrafesa.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserEmtrafRepository userEmtrafRepository;
    private final ItinerarioRepository itinerarioRepository;
    

    @Override
    public PagoDTO createPago(PagoCreateUpdateDTO pagoCreateUpdateDTO) {
        return null;
    }

    @Override
    public List<PagoDTO> getPagoHistoryByClienteId(Long clienteId) {
        return List.of();
    }

    @Override
    public List<PagoDTO> getAllPagos() {
        return List.of();
    }

    @Override
    public PagoDTO confirmPago(Long pagoId) {
        return null;
    }

    @Override
    public PagoDTO getPagoById(Long id) {
        return null;
    }

    @Override
    public List<PagoReportDTO> getPagoReportByDate(String date) {
        return List.of();
    }
}
