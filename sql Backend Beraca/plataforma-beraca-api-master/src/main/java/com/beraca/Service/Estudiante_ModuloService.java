package com.beraca.Service;

import com.beraca.Repository.Estudiante_ModuloRepository;
import com.beraca.model.Estudiante_Modulo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Estudiante_ModuloService {

    private final Estudiante_ModuloRepository estudianteModuloRepository;

    public Estudiante_ModuloService(
            Estudiante_ModuloRepository estudianteModuloRepository) {

        this.estudianteModuloRepository = estudianteModuloRepository;
    }

    // POST
    public Estudiante_Modulo guardar(
            Estudiante_Modulo estudiante_Modulo) {

        return estudianteModuloRepository.save(estudiante_Modulo);
    }

    // GET
    public List<Estudiante_Modulo> obtenerTodos() {

        return estudianteModuloRepository.findAll();
    }

    // GET POR ID
    public Estudiante_Modulo buscar(Long estudiante_id) {

        return estudianteModuloRepository
                .findById(estudiante_id)
                .orElse(null);
    }

    // DELETE
    public boolean eliminar(Long estudiante_id) {

        if (estudianteModuloRepository.existsById(estudiante_id)) {

            estudianteModuloRepository.deleteById(estudiante_id);

            return true;
        }

        return false;
    }
}