package com.beraca.Controller;

import com.beraca.Service.ProfesorService;
import com.beraca.Service.UsuarioService;
import com.beraca.model.Modulo;
import com.beraca.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final ProfesorService profesorService;

    public AuthController(
            UsuarioService usuarioService,
            ProfesorService profesorService) {

        this.usuarioService = usuarioService;
        this.profesorService = profesorService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {

        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()
                || usuario.getPassword() == null || usuario.getPassword().isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body("Debe ingresar correo y contraseña.");
        }

        Usuario usuarioBD = usuarioService.login(
                usuario.getCorreo(),
                usuario.getPassword());

        if (usuarioBD == null) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Correo o contraseña incorrectos.");

        }

        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("id", usuarioBD.getId());
        respuesta.put("nombre", usuarioBD.getNombre());
        respuesta.put("correo", usuarioBD.getCorreo());
        respuesta.put("rol", usuarioBD.getRol());

        // Si es profesor, devolver también su módulo
        if ("PROFESOR".equalsIgnoreCase(usuarioBD.getRol())) {

            Modulo modulo = profesorService.obtenerModuloProfesor(usuarioBD.getId());

            respuesta.put("modulo", modulo);

        }

        return ResponseEntity.ok(respuesta);

    }

}