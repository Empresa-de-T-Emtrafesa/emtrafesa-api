package com.emtrafesa.service;

import com.emtrafesa.dto.RutaDTO;
import com.emtrafesa.dto.RutaResponseDTO;
import com.emtrafesa.model.entity.Ruta;
import com.emtrafesa.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    public Set<String> obtenerDestinosPorOrigen(String origen) {
        List<Ruta> rutas = rutaRepository.findAll();
        Set<String> destinos = new HashSet<>();
        for (Ruta ruta : rutas) {
            if (ruta.getOrigen().equals(origen)) { // Solo agregar destinos que coincidan con el origen
                destinos.add(ruta.getDestino());
            }
        }
        return destinos;
    }


    public List<Ruta> obtenerRutas() {
        return rutaRepository.findAll(); // Obtener todas las rutas de la base de datos
    }

    public void eliminarRuta(Long id) {
        // Aquí eliminas la ruta por ID de la base de datos, por ejemplo:
        rutaRepository.deleteById(id);
    }


    public Ruta editarRuta(Long id, RutaDTO rutaDTO) {
        Ruta rutaExistente = rutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        // Actualizar los datos de la ruta
        rutaExistente.setOrigen(rutaDTO.getOrigen());
        rutaExistente.setDestino(rutaDTO.getDestino());
        rutaExistente.setTieneEscalas(rutaDTO.getTieneEscalas());

        return rutaRepository.save(rutaExistente); // Guardar la ruta editada
    }

    public Ruta actualizarRuta(Ruta ruta) {
        Optional<Ruta> rutaExistente = rutaRepository.findById(ruta.getId());
        if (rutaExistente.isPresent()) {
            Ruta rutaActualizada = rutaExistente.get();
            // Aquí puedes actualizar las propiedades que desees
            rutaActualizada.setOrigen(ruta.getOrigen());
            rutaActualizada.setDestino(ruta.getDestino());
            rutaActualizada.setTieneEscalas(ruta.getTieneEscalas());
            return rutaRepository.save(rutaActualizada);
        } else {
            return null;
        }
    }


}

