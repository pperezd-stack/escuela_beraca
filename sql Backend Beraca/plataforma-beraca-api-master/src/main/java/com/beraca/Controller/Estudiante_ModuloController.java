package com.beraca.Controller;

import com.beraca.model.Estudiante_Modulo;
import com.beraca.model.Usuario;
import com.beraca.Security.SecurityEstudiante_Modulo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modulosEstudiante")
@CrossOrigin(origins = "*")
public class Estudiante_ModuloController {

    // GET
    @GetMapping
    public String obtenermoduloEstudiantes(

            @RequestParam String rol
    ) {

        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if(!SecurityEstudiante_Modulo
                .puedeVerAsignacion(usuario)){

            return "Acceso denegado";
        }

        return "Lista de moduloEstudiantes";
    }

    // POST
    @PostMapping("/crear")
    public Object crearEstudiante_Modulo(

            @RequestBody Estudiante_Modulo estudiante_Modulo,

            @RequestParam String rol
    ) {

        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if(!SecurityEstudiante_Modulo
                .puedeCrearAsignacion(usuario)){

            return "Acceso denegado";
        }

        if(!SecurityEstudiante_Modulo
                .asignacionValida(estudiante_Modulo)){

            return "Datos inválidos";
        }

        return estudiante_Modulo;
    }

    // PUT
    @PutMapping("/actualizar")
    public String actualizarEstudiante_Modulo(

            @RequestBody Estudiante_Modulo estudiante_Modulo,

            @RequestParam String rol
    ) {

        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if(!SecurityEstudiante_Modulo
                .puedeActualizarAsignacion(usuario)){

            return "Acceso denegado";
        }

        return "Estudiante_Modulo actualizado";
    }

    // DELETE
    @DeleteMapping("/eliminar/{id}")
    public String eliminarEstudiante_Modulo(

            @PathVariable Long id,

            @RequestParam String rol
    ) {

        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if(!SecurityEstudiante_Modulo
                .puedeEliminarAsignacion(usuario)){

            return "Acceso denegado";
        }

        return "Estudiante_Modulo eliminado con ID: " + id;
    }
}