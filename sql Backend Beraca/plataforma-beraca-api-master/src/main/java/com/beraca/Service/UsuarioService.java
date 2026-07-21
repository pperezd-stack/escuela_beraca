package com.beraca.Service;

import com.beraca.Repository.UsuarioRepository;
import com.beraca.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UsuarioService {

 private final UsuarioRepository usuarioRepository;
private final BCryptPasswordEncoder passwordEncoder;

   public UsuarioService( UsuarioRepository usuarioRepository,  BCryptPasswordEncoder passwordEncoder) {

    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;

}

    // POST - GUARDAR USUARIO
    public Usuario guardar(Usuario usuario) {

    usuario.setPassword(

            passwordEncoder.encode(
                    usuario.getPassword()
            )

    );

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
   // LOGIN
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
 public boolean existeNombre(String nombre){

    return usuarioRepository
            .findByNombre(nombre)
            .isPresent();

}

}
