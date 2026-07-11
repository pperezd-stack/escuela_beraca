package com.beraca.Controller;

import com.beraca.Service.CalificacionService;
import com.beraca.model.Calificacion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
@CrossOrigin(origins = "*")
public class CalificacionController {

    private final CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    // ==========================
    // OBTENER TODAS
    // ==========================
    @GetMapping
    public ResponseEntity<List<Calificacion>> obtenerCalificaciones() {

        return ResponseEntity.ok(calificacionService.obtenerTodos());

    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCalificacion(@PathVariable Long id) {

        Calificacion calificacion = calificacionService.buscar(id);

        if (calificacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Calificación no encontrada.");
        }

        return ResponseEntity.ok(calificacion);

    }

    // ==========================
    // CREAR
    // ==========================
    @PostMapping("/crear")
    public ResponseEntity<?> crearCalificacion(
            @RequestBody Calificacion calificacion) {

        try {

            Calificacion nueva = calificacionService.guardar(calificacion);

            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================
    // ACTUALIZAR
    // ==========================
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarCalificacion(
            @RequestBody Calificacion calificacion) {

        try {

            Calificacion actualizada = calificacionService.actualizar(calificacion);

            return ResponseEntity.ok(actualizada);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================
    // ELIMINAR
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCalificacion(
            @PathVariable Long id) {

        boolean eliminado = calificacionService.eliminar(id);

        if (!eliminado) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Calificación no encontrada.");

        }

        return ResponseEntity.ok("Calificación eliminada correctamente.");

    }

}