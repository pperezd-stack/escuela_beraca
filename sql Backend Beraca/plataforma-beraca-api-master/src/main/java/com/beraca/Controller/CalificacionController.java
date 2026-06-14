package com.beraca.Controller;

import com.beraca.Service.CalificacionService;
import com.beraca.model.Calificacion;
import com.beraca.model.Usuario;
import com.beraca.Security.SecurityCalificacion;

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

    // POST - CREAR O ACTUALIZAR CALIFICACIÓN
    @PostMapping("/crear")
    public Object crearCalificacion(
            @RequestBody Calificacion calificacion,
            @RequestParam String rol
    ) {
        // Creamos un usuario ficticio local únicamente para la validación de roles de seguridad
        Usuario usuarioValidacion = new Usuario();
        usuarioValidacion.setRol(rol);

        // VALIDAR PERMISOS DE ROL
        if (!SecurityCalificacion.puedeCrearCalificacion(usuarioValidacion)) {
            return "Acceso denegado";
        }

        // VALIDAR DATOS DE LA CALIFICACIÓN
        if (!SecurityCalificacion.calificacionValida(calificacion)) {
            return "Datos inválidos";
        }

        // Guarda directamente la calificación sin arrastrar contextos de otras entidades
        return calificacionService.guardar(calificacion);
    }

    // GET - OBTENER TODAS LAS CALIFICACIONES (O FILTRADAS POR ESTUDIANTE)
    @GetMapping
    public Object obtenerCalificaciones(
            @RequestParam String rol,
            @RequestParam(required = false) Long estudianteId
    ) {
        Usuario usuarioValidacion = new Usuario();
        usuarioValidacion.setRol(rol);

        if (!SecurityCalificacion.puedeVerCalificacion(usuarioValidacion)) {
            return "Acceso denegado";
        }

        // Buscamos usando el getter con guion bajo idóneo para tu modelo actual
        if (estudianteId != null) {
            return calificacionService.obtenerTodos().stream()
                    .filter(c -> c.getEstudiante_id() != null && c.getEstudiante_id().equals(estudianteId))
                    .toList();
        }

        return calificacionService.obtenerTodos();
    }

    // GET BY ID - BUSCAR UNA CALIFICACIÓN
    @GetMapping("/{id}")
    public Object buscarCalificacion(
            @PathVariable Long id,
            @RequestParam String rol
    ) {
        Usuario usuarioValidacion = new Usuario();
        usuarioValidacion.setRol(rol);

        if (!SecurityCalificacion.puedeVerCalificacion(usuarioValidacion)) {
            return "Acceso denegado";
        }

        return calificacionService.buscar(id);
    }

    // DELETE - ELIMINAR CALIFICACIÓN
    @DeleteMapping("/{id}")
    public String eliminarCalificacion(
            @PathVariable Long id,
            @RequestParam String rol
    ) {
        Usuario usuarioValidacion = new Usuario();
        usuarioValidacion.setRol(rol);

        if (!SecurityCalificacion.puedeEliminarCalificacion(usuarioValidacion)) {
            return "Acceso denegado";
        }

        boolean eliminado = calificacionService.eliminar(id);

        if (eliminado) {
            return "Calificación eliminada";
        }

        return "Calificación no encontrada";
    }
}