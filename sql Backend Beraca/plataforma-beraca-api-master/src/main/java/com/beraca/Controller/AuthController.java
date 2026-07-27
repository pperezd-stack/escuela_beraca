package com.beraca.Controller;

import com.beraca.Repository.ModuloRepository;
import com.beraca.Service.UsuarioService;
import com.beraca.dto.UsuarioDTO;
import com.beraca.model.Modulo;
import com.beraca.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final ModuloRepository moduloRepository;

    public AuthController(UsuarioService usuarioService, ModuloRepository moduloRepository) {
        this.usuarioService = usuarioService;
        this.moduloRepository = moduloRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario datos) {
        Usuario usuario = usuarioService.login(
                datos.getNombre(),
                datos.getPassword()
        );

        if (usuario == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Usuario o contraseña incorrectos");
        }

        // 🟢 Si el usuario es PROFESOR, buscamos el módulo que tiene asignado
        Long moduloId = null;
        if ("PROFESOR".equalsIgnoreCase(usuario.getRol())) {
            Modulo modulo = moduloRepository.findByProfesorId(usuario.getId()).orElse(null);
            if (modulo != null) {
                moduloId = modulo.getId();
            }
        }

        UsuarioDTO respuesta = new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getRol(),
                moduloId
        );

        return ResponseEntity.ok(respuesta);
    }
}
