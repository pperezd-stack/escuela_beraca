package com.beraca.Service;

import com.beraca.Repository.CalificacionRepository;
import com.beraca.model.Calificacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {

    private final CalificacionRepository calificacionRepository;

    public CalificacionService(
            CalificacionRepository calificacionRepository) {

        this.calificacionRepository = calificacionRepository;
    }

    // POST
    public Calificacion guardar(Calificacion calificacion) {

        return calificacionRepository.save(calificacion);
    }

    // GET
    public List<Calificacion> obtenerTodos() {

        return calificacionRepository.findAll();
    }

    // GET ID
    public Calificacion buscar(Long id) {

        return calificacionRepository.findById(id)
                .orElse(null);
    }

    // DELETE
    public boolean eliminar(Long id) {

        if (calificacionRepository.existsById(id)) {

            calificacionRepository.deleteById(id);

            return true;
        }

        return false;
    }
}



