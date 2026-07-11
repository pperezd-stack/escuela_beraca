package com.beraca.Repository;

import com.beraca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar solo estudiantes
    @Query(value = """
            SELECT *
            FROM usuarios
            WHERE UPPER(TRIM(rol)) = 'ESTUDIANTE'
            """, nativeQuery = true)
    List<Usuario> buscarSoloEstudiantes();

    // Buscar solo profesores
    @Query(value = """
            SELECT *
            FROM usuarios
            WHERE UPPER(TRIM(rol)) = 'PROFESOR'
            """, nativeQuery = true)
    List<Usuario> buscarSoloProfesores();

    // Login
    Optional<Usuario> findByCorreoAndPassword(String correo, String password);

    // Buscar por correo
    Optional<Usuario> findByCorreo(String correo);

    // Buscar varios usuarios por sus IDs
    List<Usuario> findByIdIn(List<Long> ids);

}