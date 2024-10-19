package com.emtrafesa.service;

import com.emtrafesa.dto.RutaDTO;
import com.emtrafesa.model.entity.Ruta;
import com.emtrafesa.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public Ruta crearRuta(RutaDTO rutaDTO) {
        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setOrigen(rutaDTO.getOrigen());
        nuevaRuta.setDestino(rutaDTO.getDestino());
        nuevaRuta.setTieneEscalas(rutaDTO.getTieneEscalas());

        // Aquí puedes buscar el empleado si es necesario
        // Si deseas asignar un empleado a la ruta, deberás validar que exista

        return rutaRepository.save(nuevaRuta);
    }

    public Set<String> obtenerOrígenes() {
        List<Ruta> rutas = rutaRepository.findAll();
        Set<String> orígenes = new HashSet<>();
        for (Ruta ruta : rutas) {
            orígenes.add(ruta.getOrigen());
        }
        return orígenes;
    }

    public Set<String> obtenerDestinos() {
        List<Ruta> rutas = rutaRepository.findAll();
        Set<String> destinos = new HashSet<>();
        for (Ruta ruta : rutas) {
            destinos.add(ruta.getDestino());
        }
        return destinos;
    }
}