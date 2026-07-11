package com.beraca.Service;

import com.beraca.Repository.EstudianteModuloRepository;
import com.beraca.Repository.ModuloRepository;
import com.beraca.Repository.UsuarioRepository;
import com.beraca.model.Estudiante_Modulo;
import com.beraca.model.Modulo;
import com.beraca.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorService {

    private final UsuarioRepository usuarioRepository;
    private final ModuloRepository moduloRepository;
    private final EstudianteModuloRepository estudianteModuloRepository;

    public ProfesorService(
            UsuarioRepository usuarioRepository,
            ModuloRepository moduloRepository,
            EstudianteModuloRepository estudianteModuloRepository) {

        this.usuarioRepository = usuarioRepository;
        this.moduloRepository = moduloRepository;
        this.estudianteModuloRepository = estudianteModuloRepository;
    }

    // ==========================================
    // MÓDULO DEL PROFESOR
    // ==========================================
    public Modulo obtenerModuloProfesor(Long profesorId) {

        return moduloRepository
                .findByProfesor_Id(profesorId)
                .orElse(null);

    }

    // ==========================================
    // ESTUDIANTES DEL MÓDULO DEL PROFESOR
    // ==========================================
    public List<Usuario> obtenerEstudiantesDelProfesor(Long profesorId){

        Modulo modulo = obtenerModuloProfesor(profesorId);

        if(modulo == null){
            return new ArrayList<>();
        }

        List<Estudiante_Modulo> matriculas =
                estudianteModuloRepository.findByModulo_Id(modulo.getId());

        List<Usuario> estudiantes = new ArrayList<>();

        for(Estudiante_Modulo em : matriculas){
            estudiantes.add(em.getEstudiante());
        }

        return estudiantes;
    }

    // ==========================================
    // CAMBIAR UN ESTUDIANTE DE MÓDULO
    // ==========================================
    public Estudiante_Modulo cambiarModuloEstudiante(
            Long estudianteId,
            Long nuevoModuloId){

        Estudiante_Modulo matricula =
                estudianteModuloRepository
                        .findByEstudiante_Id(estudianteId)
                        .orElseThrow(() ->
                                new RuntimeException("El estudiante no está matriculado."));

        Modulo modulo =
                moduloRepository.findById(nuevoModuloId)
                        .orElseThrow(() ->
                                new RuntimeException("El módulo no existe."));

        matricula.setModulo(modulo);

        return estudianteModuloRepository.save(matricula);

    }

    // ==========================================
    // ELIMINAR MATRÍCULA
    // ==========================================
    public void eliminarMatricula(Long estudianteId){

        Estudiante_Modulo matricula =
                estudianteModuloRepository
                        .findByEstudiante_Id(estudianteId)
                        .orElseThrow(() ->
                                new RuntimeException("El estudiante no está matriculado."));

        estudianteModuloRepository.delete(matricula);

    }

}