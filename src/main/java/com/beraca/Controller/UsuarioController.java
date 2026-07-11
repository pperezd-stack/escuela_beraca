package com.beraca.Controller;

import com.beraca.Service.UsuarioService;
import com.beraca.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // ==========================
    // CREAR USUARIO
    // ==========================
    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {

        try {
            Usuario nuevo = usuarioService.guardar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    // ==========================
    // LISTAR TODOS
    // ==========================
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {

        return ResponseEntity.ok(usuarioService.obtenerTodos());

    }

    // ==========================
    // LISTAR SOLO ESTUDIANTES
    // ==========================
    @GetMapping("/estudiantes")
    public ResponseEntity<List<Usuario>> obtenerEstudiantes() {

        return ResponseEntity.ok(usuarioService.obtenerEstudiantes());

    }

    // ==========================
    // LISTAR SOLO PROFESORES
    // ==========================
    @GetMapping("/profesores")
    public ResponseEntity<List<Usuario>> obtenerProfesores() {

        return ResponseEntity.ok(usuarioService.obtenerProfesores());

    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Long id) {

        Usuario usuario = usuarioService.buscar(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        return ResponseEntity.ok(usuario);

    }

    // ==========================
    // ELIMINAR
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {

        boolean eliminado = usuarioService.eliminar(id);

        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        return ResponseEntity.ok("Usuario eliminado correctamente");

    }

}