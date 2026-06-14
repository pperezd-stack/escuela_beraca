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

    public Observacion guardar(Observacion observacion) {
        return observacionRepository.save(observacion);
    }

    public List<Observacion> obtenerTodos() {
        return observacionRepository.findAll();
    }

    public boolean eliminar(Long id) {
        if (observacionRepository.existsById(id)) {
            observacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}