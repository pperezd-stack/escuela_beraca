package com.beraca.Controller;

import com.beraca.Service.UsuarioService;
import com.beraca.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // CREAR USUARIO
    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    // OBTENER TODOS LOS USUARIOS
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerTodos();
    }

    // BUSCAR USUARIO POR ID
    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscar(id);
    }

    // ELIMINAR USUARIO
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {

        boolean eliminado = usuarioService.eliminar(id);

        if (eliminado) {
            return "Usuario eliminado";
        }

        return "Usuario no encontrado";
    }
}
