package com.beraca.Service;

import com.beraca.Repository.UsuarioRepository;
import com.beraca.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // ==========================
    // REGISTRAR USUARIO
    // ==========================
    public Usuario guardar(Usuario usuario) {

        validarUsuario(usuario);

        return usuarioRepository.save(usuario);
    }

    // ==========================
    // LOGIN
    // ==========================
    public Usuario login(String correo, String password) {

        return usuarioRepository
                .findByCorreoAndPassword(correo, password)
                .orElse(null);
    }

    // ==========================
    // BUSCAR POR ID
    // ==========================
    public Usuario buscar(Long id) {

        return usuarioRepository.findById(id).orElse(null);
    }

    // ==========================
    // BUSCAR POR CORREO
    // ==========================
    public Usuario buscarPorCorreo(String correo) {

        return usuarioRepository
                .findByCorreo(correo)
                .orElse(null);
    }

    // ==========================
    // LISTAR ESTUDIANTES
    // ==========================
    public List<Usuario> obtenerEstudiantes() {

        return usuarioRepository.buscarSoloEstudiantes();
    }

    // ==========================
    // LISTAR PROFESORES
    // ==========================
    public List<Usuario> obtenerProfesores() {

        return usuarioRepository.buscarSoloProfesores();
    }

    // ==========================
    // LISTAR TODOS
    // ==========================
    public List<Usuario> obtenerTodos() {

        return usuarioRepository.findAll();
    }

    // ==========================
    // ELIMINAR
    // ==========================
    public boolean eliminar(Long id) {

        if (!usuarioRepository.existsById(id)) {
            return false;
        }

        usuarioRepository.deleteById(id);
        return true;
    }

    // ==========================
    // VALIDACIONES
    // ==========================
    private void validarUsuario(Usuario usuario) {

        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }

        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            throw new RuntimeException("El rol es obligatorio");
        }

        Optional<Usuario> existente = usuarioRepository.findByCorreo(usuario.getCorreo());

        if (existente.isPresent()) {

            if (usuario.getId() == null ||
                    !existente.get().getId().equals(usuario.getId())) {

                throw new RuntimeException("El correo ya está registrado");
            }
        }
    }

}