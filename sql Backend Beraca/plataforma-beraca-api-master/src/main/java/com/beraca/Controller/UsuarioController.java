package com.beraca.Controller;

import com.beraca.Service.UsuarioService;
import com.beraca.dto.EstudianteRegistroDTO;
import com.beraca.dto.ProfesorRegistroDTO;
import com.beraca.model.Usuario;
import com.beraca.model.Modulo; // Asegúrate de importar tu modelo Modulo o DTO correspondiente
import com.beraca.Service.ModuloService; // Asegúrate de tener tu servicio de módulos
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ModuloService moduloService; // Inyectado para listar los módulos

    public UsuarioController(UsuarioService usuarioService, ModuloService moduloService) {
        this.usuarioService = usuarioService;
        this.moduloService = moduloService;
    }

    /* ===================================================
                        MÓDULOS
    ====================================================*/

    // ENDPOINT PARA LLENAR EL SELECT DE MÓDULOS EN EL REGISTRO
    @GetMapping("/modulos")
    public ResponseEntity<List<Modulo>> obtenerModulos() {
        try {
            List<Modulo> modulos = moduloService.obtenerTodos();
            return ResponseEntity.ok(modulos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /* ===================================================
                        USUARIOS
    ====================================================*/

    // CREAR PROFESOR CON MÓDULO ASOCIADO Y VALIDACIÓN
    @PostMapping("/usuarios/crear-profesor")
    public ResponseEntity<?> crearProfesor(@RequestBody ProfesorRegistroDTO dto) {
        try {
            Usuario nuevoProfesor = usuarioService.guardarProfesorConModulo(dto);
            return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar el profesor: " + e.getMessage());
        }
    }

    // CREAR ESTUDIANTE CON MÓDULO ASOCIADO
    @PostMapping("/usuarios/crear-estudiante")
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

    // CREAR USUARIO GENÉRICO
    @PostMapping("/usuarios/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    // OBTENER TODOS LOS USUARIOS
    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerTodos();
    }

    // BUSCAR USUARIO POR ID
    @GetMapping("/usuarios/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscar(id);
    }

    // ELIMINAR USUARIO
    @DeleteMapping("/usuarios/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminar(id);
        if (eliminado) {
            return "Usuario eliminado";
        }
        return "Usuario no encontrado";
    }

    // OBTENER ESTUDIANTES FILTRADOS POR MÓDULO DEL PROFESOR
    @GetMapping("/usuarios/estudiantes-por-modulo")
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
