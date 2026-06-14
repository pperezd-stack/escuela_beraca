package com.beraca.Controller;

import com.beraca.Service.ObservacionService;
import com.beraca.model.Observacion;
import com.beraca.model.Usuario;
import com.beraca.Security.SecurityObservacion;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/observaciones")
@CrossOrigin(origins = "*")
public class ObservacionController {

    private final ObservacionService observacionService;

    public ObservacionController(ObservacionService observacionService) {
        this.observacionService = observacionService;
    }

    @GetMapping
    public Object obtenerObservaciones(@RequestParam String rol) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if (!SecurityObservacion.puedeVerObservacion(usuario)) {
            return "Acceso denegado";
        }
        return observacionService.obtenerTodos();
    }

    @PostMapping("/crear")
    public Object crearObservacion(@RequestBody Observacion observacion, @RequestParam String rol) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        // Validamos permisos
        if (!SecurityObservacion.puedeCrearObservacion(usuario)) {
            return "Acceso denegado";
        }

        // Esta validación ahora debe verificar los 3 cortes en SecurityObservacion
        if (!SecurityObservacion.observacionValida(observacion)) {
            return "Datos inválidos: Asegúrese de enviar los comentarios de los cortes";
        }

        return observacionService.guardar(observacion);
    }

    @PutMapping("/actualizar")
    public Object actualizarObservacion(@RequestBody Observacion observacion, @RequestParam String rol) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if (!SecurityObservacion.puedeActualizarObservacion(usuario)) {
            return "Acceso denegado";
        }

        // Al igual que crear, el objeto 'observacion' ya trae los 3 campos gracias a Spring
        return observacionService.guardar(observacion);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarObservacion(@PathVariable Long id, @RequestParam String rol) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if (!SecurityObservacion.puedeEliminarObservacion(usuario)) {
            return "Acceso denegado";
        }

        boolean eliminado = observacionService.eliminar(id);
        return eliminado ? "Observación eliminada con ID: " + id : "Observación no encontrada";
    }
}