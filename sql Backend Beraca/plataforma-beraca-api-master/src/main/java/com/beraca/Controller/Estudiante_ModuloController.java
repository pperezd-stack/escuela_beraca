package com.beraca.Controller;

import com.beraca.model.Estudiante_Modulo;
import com.beraca.model.Usuario;
import com.beraca.Repository.Estudiante_ModuloRepository;
import com.beraca.Security.SecurityEstudiante_Modulo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulosEstudiante")
@CrossOrigin(origins = "*")
public class Estudiante_ModuloController {

    private final Estudiante_ModuloRepository estudianteModuloRepository;

    public Estudiante_ModuloController(Estudiante_ModuloRepository estudianteModuloRepository) {
        this.estudianteModuloRepository = estudianteModuloRepository;
    }

    // GET - Listar todas las asignaciones (matrículas)
    @GetMapping
    public ResponseEntity<?> obtenerModuloEstudiantes(@RequestParam String rol) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if (!SecurityEstudiante_Modulo.puedeVerAsignacion(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }

        List<Estudiante_Modulo> lista = estudianteModuloRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    // POST - Crear una nueva asignación (matrícula)
    @PostMapping("/crear")
    public ResponseEntity<?> crearEstudianteModulo(
            @RequestBody Estudiante_Modulo estudianteModulo,
            @RequestParam String rol
    ) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if (!SecurityEstudiante_Modulo.puedeCrearAsignacion(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }

        if (!SecurityEstudiante_Modulo.asignacionValida(estudianteModulo)) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        Estudiante_Modulo guardado = estudianteModuloRepository.save(estudianteModulo);
        return ResponseEntity.ok(guardado);
    }

    // PUT - Actualizar una asignación existente
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarEstudianteModulo(
            @RequestBody Estudiante_Modulo estudianteModulo,
            @RequestParam String rol
    ) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if (!SecurityEstudiante_Modulo.puedeActualizarAsignacion(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }

        if (estudianteModulo.getId() == null || !estudianteModuloRepository.existsById(estudianteModulo.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asignación no encontrada");
        }

        Estudiante_Modulo actualizado = estudianteModuloRepository.save(estudianteModulo);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE - Eliminar una asignación
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarEstudianteModulo(
            @PathVariable Long id,
            @RequestParam String rol
    ) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if (!SecurityEstudiante_Modulo.puedeEliminarAsignacion(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        }

        if (!estudianteModuloRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asignación no encontrada con ID: " + id);
        }

        estudianteModuloRepository.deleteById(id);
        return ResponseEntity.ok("Estudiante_Modulo eliminado con ID: " + id);
    }
}
