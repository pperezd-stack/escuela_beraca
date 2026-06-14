package com.beraca.Controller;

import com.beraca.model.Usuario;
import com.beraca.Security.SecurityUsuario;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {

        // VALIDAR DATOS
        if(!SecurityUsuario.loginValido(usuario)){

            return "Datos incompletos";
        }

        // VALIDAR ROL
        if(SecurityUsuario.esProfesor(usuario)) {

            return "Acceso profesor";

        } else if(SecurityUsuario.esEstudiante(usuario)){

            return "Acceso estudiante";
        }

        return "Rol no válido";
    }
}