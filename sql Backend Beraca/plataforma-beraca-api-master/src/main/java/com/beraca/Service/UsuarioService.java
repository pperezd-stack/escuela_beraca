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

    // GET - OBTENER TODOS LOS USUARIOS (SIN FILTRAR)
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // GET - OBTENER SOLO ESTUDIANTES DESDE LA BASE DE DATOS
    public List<Usuario> obtenerEstudiantes() {
        return usuarioRepository.buscarSoloEstudiantes();
    }

    // GET ID - BUSCAR POR ID
    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // DELETE - ELIMINAR USUARIO
    public boolean eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
public Usuario login(String correo, String password){

    Usuario usuario =
            usuarioRepository.findByCorreo(correo).orElse(null);

    if(usuario == null){
        return null;
    }

    if(!usuario.getPassword().equals(password)){
        return null;
    }

    return usuario;

}
    
}
