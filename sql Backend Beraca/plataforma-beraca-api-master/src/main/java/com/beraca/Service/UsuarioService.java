package com.beraca.Service;

import com.beraca.Repository.UsuarioRepository;
import com.beraca.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // POST - GUARDAR USUARIO
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // GET - OBTENER TODOS LOS USUARIOS
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // GET - OBTENER SOLO ESTUDIANTES
    public List<Usuario> obtenerEstudiantes() {
        return usuarioRepository.buscarSoloEstudiantes();
    }

    // GET - BUSCAR POR ID
    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // DELETE
    public boolean eliminar(Long id) {

        if (usuarioRepository.existsById(id)) {

            usuarioRepository.deleteById(id);
            return true;

        }

        return false;
    }

    // LOGIN
    public Usuario login(String nombre) {

        return usuarioRepository
                .findByNombre(nombre)
                .orElse(null);

    }

}
