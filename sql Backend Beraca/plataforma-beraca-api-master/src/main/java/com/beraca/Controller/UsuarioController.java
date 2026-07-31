package com.beraca.Controller;

import com.beraca.Service.UsuarioService;
import com.beraca.dto.EstudianteRegistroDTO;
import com.beraca.dto.ProfesorRegistroDTO;
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

    // CREAR PROFESOR CON MÓDULO ASOCIADO Y VALIDACIÓN
    @PostMapping("/crear-profesor")
    public ResponseEntity<?> crearProfesor(@RequestBody ProfesorRegistroDTO dto) {
        try {
            Usuario nuevoProfesor = usuarioService.guardarProfesorConModulo(dto);
            return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Devuelve error 400 con el mensaje claro si el módulo ya tiene profesor
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar el profesor: " + e.getMessage());
        }
    }

    // 🟢 ACTUALIZADO: CREAR ESTUDIANTE CON MÓDULO ASOCIADO (Usa EstudianteRegistroDTO)
    @PostMapping("/crear-estudiante")
    public ResponseEntity<?> crearEstudiante(@RequestBody EstudianteRegistroDTO dto) {
        try {
            Usuario nuevoEstudiante = usuarioService.guardarEstudianteConModulo(dto);
            return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar el estudiante: " + e.getMessage());
        }
    }

    // CREAR USUARIO GENÉRICO (si lo usas para otros roles)
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

    // OBTENER ESTUDIANTES FILTRADOS POR MÓDULO DEL PROFESOR
    @GetMapping("/estudiantes-por-modulo")
    public ResponseEntity<?> obtenerEstudiantesPorModulo(@RequestParam String modulo) {
        try {
            List<Usuario> estudiantes = usuarioService.obtenerEstudiantesPorModulo(modulo);
            return ResponseEntity.ok(estudiantes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los estudiantes del módulo: " + e.getMessage());
        }
    }
}
