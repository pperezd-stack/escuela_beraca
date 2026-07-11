package com.beraca.Controller;

import com.beraca.Service.ModuloService;
import com.beraca.model.Modulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulos")
@CrossOrigin(origins = "*")
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    // ==========================
    // OBTENER TODOS
    // ==========================
    @GetMapping
    public List<Modulo> obtenerModulos() {

        return moduloService.obtenerTodos();

    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarModulo(@PathVariable Long id) {

        Modulo modulo = moduloService.buscar(id);

        if (modulo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(modulo);

    }

    // ==========================
    // CREAR
    // ==========================
    @PostMapping("/crear")
    public ResponseEntity<?> crearModulo(
            @RequestBody Modulo modulo) {

        try {

            return ResponseEntity.ok(
                    moduloService.guardar(modulo));

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }

    }

    // ==========================
    // ACTUALIZAR
    // ==========================
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarModulo(
            @RequestBody Modulo modulo) {

        try {

            return ResponseEntity.ok(
                    moduloService.actualizar(modulo));

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());

        }

    }

    // ==========================
    // ELIMINAR
    // ==========================
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarModulo(
            @PathVariable Long id) {

        if (moduloService.eliminar(id)) {

            return ResponseEntity.ok("Módulo eliminado correctamente.");

        }

        return ResponseEntity.badRequest()
                .body("El módulo no existe.");

    }

    // ==========================
    // MÓDULO DEL PROFESOR
    // ==========================
    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<?> obtenerModuloProfesor(
            @PathVariable Long profesorId) {

        Modulo modulo =
                moduloService.obtenerModuloProfesor(profesorId);

        if (modulo == null) {

            return ResponseEntity.badRequest()
                    .body("El profesor no tiene un módulo asignado.");

        }

        return ResponseEntity.ok(modulo);

    }

}