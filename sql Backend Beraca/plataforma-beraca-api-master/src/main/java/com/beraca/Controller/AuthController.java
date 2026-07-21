package com.beraca.Controller;

import com.beraca.Service.UsuarioService;
import com.beraca.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario datos){

        Usuario usuario =
                usuarioService.login(
                        datos.getCorreo(),
                        datos.getPassword()
                );

        if(usuario == null){

            return ResponseEntity
                    .badRequest()
                    .body("Correo o contraseña incorrectos");

        }

        return ResponseEntity.ok(usuario);

    }

}
