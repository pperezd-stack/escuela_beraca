package com.beraca.Controller;

import com.beraca.Service.ProfesorService;
import com.beraca.model.Estudiante_Modulo;
import com.beraca.model.Modulo;
import com.beraca.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
@CrossOrigin(origins = "*")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    // ==========================================
    // MÓDULO DEL PROFESOR
    // ==========================================
    @GetMapping("/{profesorId}/modulo")
    public ResponseEntity<Modulo> obtenerModuloProfesor(
            @PathVariable Long profesorId) {

        Modulo modulo = profesorService.obtenerModuloProfesor(profesorId);

        if (modulo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(modulo);
    }

    // ==========================================
    // ESTUDIANTES DEL MÓDULO DEL PROFESOR
    // ==========================================
    @GetMapping("/{profesorId}/estudiantes")
    public ResponseEntity<List<Usuario>> obtenerEstudiantesModulo(
            @PathVariable Long profesorId) {

        return ResponseEntity.ok(
                profesorService.obtenerEstudiantesDelProfesor(profesorId)
        );
    }

    // ==========================================
    // CAMBIAR ESTUDIANTE DE MÓDULO
    // ==========================================
    @PutMapping("/cambiarModulo/{estudianteId}/{nuevoModuloId}")
    public ResponseEntity<Estudiante_Modulo> cambiarModuloEstudiante(

            @PathVariable Long estudianteId,
            @PathVariable Long nuevoModuloId) {

        return ResponseEntity.ok(
                profesorService.cambiarModuloEstudiante(
                        estudianteId,
                        nuevoModuloId
                )
        );
    }

    // ==========================================
    // ELIMINAR MATRÍCULA
    // ==========================================
    @DeleteMapping("/eliminarMatricula/{estudianteId}")
    public ResponseEntity<String> eliminarMatricula(
            @PathVariable Long estudianteId) {

        profesorService.eliminarMatricula(estudianteId);

        return ResponseEntity.ok("Matrícula eliminada correctamente.");
    }

}