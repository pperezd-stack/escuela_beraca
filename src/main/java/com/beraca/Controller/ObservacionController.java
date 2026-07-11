package com.beraca.Controller;

import com.beraca.Service.ObservacionService;
import com.beraca.model.Observacion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/observaciones")
@CrossOrigin(origins = "*")
public class ObservacionController {

    private final ObservacionService observacionService;

    public ObservacionController(ObservacionService observacionService) {
        this.observacionService = observacionService;
    }

    // ==========================
    // OBTENER TODAS
    // ==========================
    @GetMapping
    public ResponseEntity<List<Observacion>> obtenerObservaciones() {

        return ResponseEntity.ok(observacionService.obtenerTodos());

    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarObservacion(@PathVariable Long id) {

        Observacion observacion = observacionService.buscar(id);

        if (observacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Observación no encontrada.");
        }

        return ResponseEntity.ok(observacion);

    }

    // ==========================
    // CREAR
    // ==========================
    @PostMapping("/crear")
    public ResponseEntity<?> crearObservacion(
            @RequestBody Observacion observacion) {

        try {

            Observacion nueva = observacionService.guardar(observacion);

            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================
    // ACTUALIZAR
    // ==========================
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarObservacion(
            @RequestBody Observacion observacion) {

        try {

            Observacion actualizada = observacionService.actualizar(observacion);

            return ResponseEntity.ok(actualizada);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    // ==========================
    // ELIMINAR
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarObservacion(
            @PathVariable Long id) {

        boolean eliminado = observacionService.eliminar(id);

        if (!eliminado) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Observación no encontrada.");

        }

        return ResponseEntity.ok("Observación eliminada correctamente.");

    }

}