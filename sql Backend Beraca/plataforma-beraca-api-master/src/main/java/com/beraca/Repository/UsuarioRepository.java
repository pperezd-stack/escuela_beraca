package com.beraca.Repository;

import com.beraca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Obtener únicamente los estudiantes
    @Query(value = "SELECT * FROM usuarios WHERE UPPER(TRIM(rol)) = 'ESTUDIANTE'", nativeQuery = true)
    List<Usuario> buscarSoloEstudiantes();

    // Buscar un usuario por su nombre
    Optional<Usuario> findByNombre(String nombre);

}
