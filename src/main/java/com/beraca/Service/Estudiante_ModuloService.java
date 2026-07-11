package com.beraca.Service;

import com.beraca.Repository.EstudianteModuloRepository;
import com.beraca.Repository.ModuloRepository;
import com.beraca.Repository.UsuarioRepository;
import com.beraca.model.Estudiante_Modulo;
import com.beraca.model.Modulo;
import com.beraca.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Estudiante_ModuloService {

    private final EstudianteModuloRepository estudianteModuloRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModuloRepository moduloRepository;

    public Estudiante_ModuloService(
            EstudianteModuloRepository estudianteModuloRepository,
            UsuarioRepository usuarioRepository,
            ModuloRepository moduloRepository) {

        this.estudianteModuloRepository = estudianteModuloRepository;
        this.usuarioRepository = usuarioRepository;
        this.moduloRepository = moduloRepository;
    }

    // ==========================
    // CREAR MATRÍCULA
    // ==========================
    public Estudiante_Modulo guardar(Estudiante_Modulo estudianteModulo) {

        validar(estudianteModulo);

        return estudianteModuloRepository.save(estudianteModulo);
    }

    // ==========================
    // ACTUALIZAR MATRÍCULA
    // ==========================
    // ==========================
// ACTUALIZAR MATRÍCULA
// ==========================
    public Estudiante_Modulo actualizar(Estudiante_Modulo estudianteModulo) {

        if (estudianteModulo.getId() == null) {
            throw new RuntimeException("Debe indicar el ID de la matrícula.");
        }

        Estudiante_Modulo existente = estudianteModuloRepository
                .findById(estudianteModulo.getId())
                .orElseThrow(() ->
                        new RuntimeException("La matrícula no existe."));

        existente.setEstudiante(estudianteModulo.getEstudiante());
        existente.setModulo(estudianteModulo.getModulo());

        return estudianteModuloRepository.save(existente);
    }

    // ==========================
    // TODAS LAS MATRÍCULAS
    // ==========================
    public List<Estudiante_Modulo> obtenerTodos() {

        return estudianteModuloRepository.findAll();

    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    public Estudiante_Modulo buscar(Long id) {

        return estudianteModuloRepository.findById(id).orElse(null);

    }

    // ==========================
    // ELIMINAR
    // ==========================
    public boolean eliminar(Long id) {

        if (!estudianteModuloRepository.existsById(id)) {
            return false;
        }

        estudianteModuloRepository.deleteById(id);

        return true;
    }

    // ==========================
    // MATRÍCULA DE UN ESTUDIANTE
    // ==========================
    public Estudiante_Modulo obtenerPorEstudiante(Long estudianteId) {

        return estudianteModuloRepository
                .findByEstudiante_Id(estudianteId)
                .orElse(null);

    }

    // ==========================
    // ESTUDIANTES DE UN MÓDULO
    // ==========================
    public List<Estudiante_Modulo> obtenerPorModulo(Long moduloId) {

        return estudianteModuloRepository.findByModulo_Id(moduloId);

    }

    // ==========================
    // CAMBIAR MÓDULO
    // ==========================
    public Estudiante_Modulo cambiarModulo(Long estudianteId,
                                           Long nuevoModuloId) {

        Estudiante_Modulo matricula =
                estudianteModuloRepository
                        .findByEstudiante_Id(estudianteId)
                        .orElseThrow(() ->
                                new RuntimeException("El estudiante no tiene matrícula."));

        Modulo modulo = moduloRepository.findById(nuevoModuloId)
                .orElseThrow(() ->
                        new RuntimeException("El módulo no existe."));

        matricula.setModulo(modulo);

        return estudianteModuloRepository.save(matricula);
    }

    // ==========================
    // VALIDACIONES
    // ==========================
    private void validar(Estudiante_Modulo estudianteModulo) {

        if (estudianteModulo.getEstudiante() == null) {
            throw new RuntimeException("Debe indicar el estudiante.");
        }

        if (estudianteModulo.getModulo() == null) {
            throw new RuntimeException("Debe indicar el módulo.");
        }

    }

}