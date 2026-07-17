package com.beraca.Controller;

import com.beraca.model.Usuario;
import org.springframework.web.bind.annotation.*;
import com.beraca.Service.UsuarioService;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // POST
    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @GetMapping
public List<Usuario> obtenerUsuarios() {
    return usuarioService.obtenerTodos();
}
    // GET ID
    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscar(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminar(id);

        if(eliminado) {
            return "Usuario eliminado";
        }

        return "Usuario no encontrado";
    }
}
