package com.beraca.Controller;

import com.beraca.model.Modulo;
import com.beraca.model.Usuario;
import com.beraca.Security.SecurityModulo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modulos")
@CrossOrigin(origins = "*")
public class ModuloController {

    // GET
    @GetMapping
    public String obtenerModulos(

            @RequestParam String rol
    ) {

        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if(!SecurityModulo
                .puedeVerModulo(usuario)){

            return "Acceso denegado";
        }

        return "Lista de módulos";
    }

    // POST
    @PostMapping("/crear")
    public Object crearModulo(

            @RequestBody Modulo modulo,

            @RequestParam String rol
    ) {

        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if(!SecurityModulo
                .puedeCrearModulo(usuario)){

            return "Acceso denegado";
        }

        if(!SecurityModulo
                .moduloValido(modulo)){

            return "Datos inválidos";
        }

        return modulo;
    }

    // PUT
    @PutMapping("/actualizar")
    public String actualizarModulo(

            @RequestBody Modulo modulo,

            @RequestParam String rol
    ) {

        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if(!SecurityModulo
                .puedeActualizarModulo(usuario)){

            return "Acceso denegado";
        }

        return "Módulo actualizado";
    }

    // DELETE
    @DeleteMapping("/eliminar/{id}")
    public String eliminarModulo(

            @PathVariable Long id,

            @RequestParam String rol
    ) {

        Usuario usuario = new Usuario();
        usuario.setRol(rol);

        if(!SecurityModulo
                .puedeEliminarModulo(usuario)){

            return "Acceso denegado";
        }

        return "Módulo eliminado con ID: " + id;
    }
}
