package com.emtrafesa.service;

import com.emtrafesa.dto.RutaDTO;
import com.emtrafesa.model.entity.Empleado;
import com.emtrafesa.model.entity.Ruta;
import com.emtrafesa.repository.EmpleadoRepository;
import com.emtrafesa.repository.ItinerarioRepository;
import com.emtrafesa.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;
    @Autowired
    private ItinerarioRepository itinerarioRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Ruta crearRuta(RutaDTO rutaDTO) {
        // Validar que el origen y destino no sean nulos o vacíos
        if (rutaDTO.getOrigen() == null || rutaDTO.getOrigen().trim().isEmpty()) {
            throw new IllegalArgumentException("El origen no puede estar vacío.");
        }

        if (rutaDTO.getDestino() == null || rutaDTO.getDestino().trim().isEmpty()) {
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        }

        if (rutaDTO.getOrigen().equalsIgnoreCase(rutaDTO.getDestino())) {
            throw new IllegalArgumentException("El origen y el destino no pueden ser iguales.");
        }

        Optional<Ruta> rutaExistente = rutaRepository.findByOrigenAndDestino(rutaDTO.getOrigen(), rutaDTO.getDestino());

        if (rutaExistente.isPresent()) {
            throw new IllegalArgumentException("La ruta con el mismo origen y destino ya existe.");
        }
        Empleado empleado = empleadoRepository.findById(rutaDTO.getEmpleadoId())
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setOrigen(rutaDTO.getOrigen());
        nuevaRuta.setDestino(rutaDTO.getDestino());
        nuevaRuta.setEmpleado(empleado);

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
    
    public void eliminarRuta(Long id){
        Ruta ruta = rutaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Ruta no encontrada"));

        itinerarioRepository.deleteByRuta(ruta);
        rutaRepository.delete(ruta);
    }
}