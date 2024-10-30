package com.emtrafesa.mapper;

import com.emtrafesa.dto.ResumenCompraDTO;
import com.emtrafesa.dto.AsientoDetalleDTO;
import com.emtrafesa.model.entity.Asiento;
import com.emtrafesa.model.entity.Pasaje;
import com.emtrafesa.model.entity.PrecioPorPiso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class ResumenCompraMapper {
    public ResumenCompraDTO toResumenCompraDTO(Pasaje pasaje) {
        ResumenCompraDTO resumen = new ResumenCompraDTO();
        resumen.setOrigen(pasaje.getItinerario().getRuta().getOrigen());
        resumen.setDestino(pasaje.getItinerario().getRuta().getDestino());
        resumen.setHoraSalida(pasaje.getItinerario().getHoraSalida().toString());
        resumen.setTipoBus(pasaje.getItinerario().getBus().getServicio().toString());
        resumen.setTieneEscalas(pasaje.getItinerario().getTieneEscalas());

        // Cargar precios en un mapa para acceso rápido
        Map<Integer, Double> preciosPorPiso = pasaje.getItinerario().getPreciosPorPiso().stream()
                .collect(Collectors.toMap(PrecioPorPiso::getPiso, PrecioPorPiso::getPrecio));

        // Crear Set para eliminar duplicados de asientos
        Set<AsientoDetalleDTO> asientosDetalle = pasaje.getPasajePasajeros().stream()
                .map(pasajePasajero -> {
                    Asiento asiento = pasajePasajero.getAsiento();
                    if (asiento == null) {
                        throw new RuntimeException("Asiento no asignado en el PasajePasajero con ID: " + pasajePasajero.getId());
                    }
                    Double precio = preciosPorPiso.getOrDefault(asiento.getPiso(), 0.0);

                    AsientoDetalleDTO detalle = new AsientoDetalleDTO();
                    detalle.setPiso(asiento.getPiso());
                    detalle.setNumeroAsiento(asiento.getNumeroAsiento());
                    detalle.setPrecio(precio);
                    return detalle;
                })
                .collect(Collectors.toSet());  // Usar Set para eliminar duplicados

        // Convertir Set a List y asignarlo al resumen
        resumen.setAsientos(new ArrayList<>(asientosDetalle));

        // Calcular el total basado en asientos únicos
        Double total = asientosDetalle.stream()
                .mapToDouble(AsientoDetalleDTO::getPrecio)
                .sum();
        resumen.setTotal(total);

        // Validar si el método de pago está presente
        if (pasaje.getPago() != null && pasaje.getPago().getMetodoPago() != null) {
            resumen.setMetodoPago(pasaje.getPago().getMetodoPago().toString());
        } else {
            resumen.setMetodoPago("Método de pago no seleccionado");
        }

        return resumen;
    }
}
