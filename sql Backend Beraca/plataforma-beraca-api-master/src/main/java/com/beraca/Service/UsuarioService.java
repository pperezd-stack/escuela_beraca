package com.beraca.Service;

import com.beraca.Repository.UsuarioRepository;
import com.beraca.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ==========================
    // REGISTRAR USUARIO
    // ==========================
    public Usuario guardar(Usuario usuario) {

        // Nombre obligatorio
        if (usuario.getNombre() == null ||
                usuario.getNombre().trim().isEmpty()) {

            throw new RuntimeException(
                    "El nombre es obligatorio"
            );
        }

        // Nombre repetido
        if (existeNombre(usuario.getNombre())) {

            throw new RuntimeException(
                    "Ese nombre ya está registrado"
            );
        }

        // Contraseña obligatoria
        if (usuario.getPassword() == null ||
                usuario.getPassword().length() < 6) {

            throw new RuntimeException(
                    "La contraseña debe tener mínimo 6 caracteres"
            );
        }

        // Rol obligatorio
        if (usuario.getRol() == null ||
                usuario.getRol().trim().isEmpty()) {

            throw new RuntimeException(
                    "Debe seleccionar un rol"
            );
        }

        // Cifrar contraseña
        usuario.setPassword(
                passwordEncoder.encode(
                        usuario.getPassword()
                )
        );

        return usuarioRepository.save(usuario);

    }

    // ==========================
    // OBTENER TODOS
    // ==========================
    public List<Usuario> obtenerTodos() {

        return usuarioRepository.findAll();

    }

    // ==========================
    // OBTENER ESTUDIANTES
    // ==========================
    public List<Usuario> obtenerEstudiantes() {

        return usuarioRepository.buscarSoloEstudiantes();

    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    public Usuario buscar(Long id) {

        return usuarioRepository
                .findById(id)
                .orElse(null);

    }

    // ==========================
    // ELIMINAR
    // ==========================
    public boolean eliminar(Long id) {

        if (usuarioRepository.existsById(id)) {

            usuarioRepository.deleteById(id);

            return true;

        }

        return false;

    }

    // ==========================
    // LOGIN
    // ==========================
    public Usuario login(String nombre, String password) {

        Usuario usuario = usuarioRepository
                .findByNombre(nombre)
                .orElse(null);

        if (usuario == null) {

            return null;

        }

        if (!passwordEncoder.matches(
                password,
                usuario.getPassword())) {

            return null;

        }

        return usuario;

    }

    // ==========================
    // VALIDAR NOMBRE EXISTENTE
    // ==========================
    public boolean existeNombre(String nombre) {

        return usuarioRepository
                .findByNombre(nombre)
                .isPresent();

    }

}
