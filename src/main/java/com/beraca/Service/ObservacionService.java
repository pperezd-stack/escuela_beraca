package com.beraca.Service;

import com.beraca.Repository.ObservacionRepository;
import com.beraca.model.Observacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservacionService {

    private final ObservacionRepository observacionRepository;

    public ObservacionService(ObservacionRepository observacionRepository) {
        this.observacionRepository = observacionRepository;
    }

    // ==========================
    // CREAR
    // ==========================
    public Observacion guardar(Observacion observacion) {

        validarObservacion(observacion);

        return observacionRepository.save(observacion);
    }

    // ==========================
    // ACTUALIZAR
    // ==========================
    public Observacion actualizar(Observacion observacion) {

        if (observacion.getId() == null) {
            throw new RuntimeException("Debe indicar el ID de la observación.");
        }

        Observacion existente = observacionRepository.findById(observacion.getId())
                .orElseThrow(() ->
                        new RuntimeException("La observación no existe."));

        existente.setEstudiante(observacion.getEstudiante());
        existente.setModulo(observacion.getModulo());
        existente.setComentarioCorte1(observacion.getComentarioCorte1());
        existente.setComentarioCorte2(observacion.getComentarioCorte2());
        existente.setComentarioCorte3(observacion.getComentarioCorte3());
        existente.setFecha(observacion.getFecha());

        validarObservacion(existente);

        return observacionRepository.save(existente);
    }

    // ==========================
    // OBTENER TODAS
    // ==========================
    public List<Observacion> obtenerTodos() {
        return observacionRepository.findAll();
    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    public Observacion buscar(Long id) {
        return observacionRepository.findById(id).orElse(null);
    }

    // ==========================
    // ELIMINAR
    // ==========================
    public boolean eliminar(Long id) {

        if (!observacionRepository.existsById(id)) {
            return false;
        }

        observacionRepository.deleteById(id);
        return true;
    }

    // ==========================
    // VALIDACIONES
    // ==========================
    private void validarObservacion(Observacion observacion) {

        if (observacion.getEstudiante() == null) {
            throw new RuntimeException("Debe indicar el estudiante.");
        }

        if (observacion.getModulo() == null) {
            throw new RuntimeException("Debe indicar el módulo.");
        }

        if (observacion.getComentarioCorte1() == null) {
            observacion.setComentarioCorte1("");
        }

        if (observacion.getComentarioCorte2() == null) {
            observacion.setComentarioCorte2("");
        }

        if (observacion.getComentarioCorte3() == null) {
            observacion.setComentarioCorte3("");
        }

        if (observacion.getFecha() == null || observacion.getFecha().isBlank()) {
            throw new RuntimeException("Debe indicar la fecha.");
        }
    }

    // ==========================
    // OBSERVACIONES DE UN ESTUDIANTE
    // ==========================
    public List<Observacion> obtenerPorEstudiante(Long estudianteId) {

        return observacionRepository.findByEstudiante_Id(estudianteId);

    }

    // ==========================
    // OBSERVACIONES DE UN MÓDULO
    // ==========================
    public List<Observacion> obtenerPorModulo(Long moduloId) {

        return observacionRepository.findByModulo_Id(moduloId);

    }

    // ==========================
    // OBSERVACIÓN DE UN ESTUDIANTE EN UN MÓDULO
    // ==========================
    public Observacion obtenerPorEstudianteYModulo(Long estudianteId,
                                                   Long moduloId) {

        return observacionRepository
                .findByEstudiante_IdAndModulo_Id(estudianteId, moduloId)
                .orElse(null);

    }

}