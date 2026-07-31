package com.beraca.Service;

import com.beraca.Repository.ModuloRepository;
import com.beraca.Repository.UsuarioRepository;
import com.beraca.dto.ProfesorRegistroDTO;
import com.beraca.model.Modulo;
import com.beraca.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModuloRepository moduloRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            ModuloRepository moduloRepository,
            BCryptPasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.moduloRepository = moduloRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ==========================
    // REGISTRAR PROFESOR CON MÓDULO (VALIDACIÓN 1 PROFESOR = 1 MÓDULO)
    // ==========================
    @Transactional
    public Usuario guardarProfesorConModulo(ProfesorRegistroDTO dto) {

        // 1. Validar nombre
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        if (existeNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ese nombre ya está registrado");
        }

        // 2. Validar contraseña
        if (dto.getPassword() == null || dto.getPassword().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener mínimo 6 caracteres");
        }

        // 3. Validar selección de módulo
        if (dto.getModuloId() == null) {
            throw new IllegalArgumentException("Debe seleccionar un módulo para el profesor.");
        }

        // 4. Buscar el módulo
        Modulo modulo = moduloRepository.findById(dto.getModuloId())
                .orElseThrow(() -> new IllegalArgumentException("El módulo seleccionado no existe."));

        // 5. Validar que el módulo NO tenga un profesor ya asignado
        if (modulo.getProfesor() != null) {
            throw new IllegalArgumentException("El módulo '" + modulo.getNombre() + "' ya tiene un profesor asignado.");
        }

        // 6. Crear objeto Usuario (Solo nombre, rol y contraseña cifrada)
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setRol("PROFESOR");
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Guardar el profesor en la base de datos
        Usuario profesorGuardado = usuarioRepository.save(usuario);

        // 7. Vincular el módulo al nuevo profesor y actualizar el módulo
        modulo.setProfesor(profesorGuardado);
        moduloRepository.save(modulo);

        return profesorGuardado;
    }

    // ==========================
    // REGISTRAR USUARIO GENÉRICO
    // ==========================
    public Usuario guardar(Usuario usuario) {

        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (existeNombre(usuario.getNombre())) {
            throw new RuntimeException("Ese nombre ya está registrado");
        }

        if (usuario.getPassword() == null || usuario.getPassword().length() < 6) {
            throw new RuntimeException("La contraseña debe tener mínimo 6 caracteres");
        }

        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
            throw new RuntimeException("Debe seleccionar un rol");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

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
        return usuarioRepository.findById(id).orElse(null);
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

        Usuario usuario = usuarioRepository.findByNombre(nombre).orElse(null);

        if (usuario == null) {
            return null;
        }

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            return null;
        }

        return usuario;
    }

    // ==========================
    // VALIDAR NOMBRE EXISTENTE
    // ==========================
    public boolean existeNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre).isPresent();
    }

    // ==========================
    // OBTENER ESTUDIANTES POR MÓDULO (BLINDADO)
    // ==========================
    public List<Usuario> obtenerEstudiantesPorModulo(String moduloParam) {
        if (moduloParam == null || moduloParam.trim().isEmpty()) {
            throw new IllegalArgumentException("El módulo no puede estar vacío");
        }

        // Limpieza de seguridad: si llega con formato "10:1", extrae únicamente la primera parte ("10")
        String moduloLimpio = moduloParam.split(":")[0].trim();

        // Realiza la consulta adaptada a tu repositorio con la tabla 'estudiante_modulo'
        return usuarioRepository.findByRolAndModulo("ESTUDIANTE", moduloLimpio);
    }
}    
