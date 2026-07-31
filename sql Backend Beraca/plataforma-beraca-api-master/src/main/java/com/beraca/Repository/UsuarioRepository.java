package com.beraca.Repository;

import com.beraca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // Obtener estudiantes de un módulo específico (verificando el nombre real de tu tabla intermedia)
    @Query(value = """
        SELECT u.* FROM usuarios u
        JOIN estudiante_modulo em ON em.estudiante_id = u.id
        WHERE UPPER(TRIM(u.rol)) = UPPER(:rol)
        AND em.modulo_id = CAST(:moduloId AS bigint)
        """, nativeQuery = true)
    List<Usuario> findByRolAndModulo(@Param("rol") String rol, @Param("moduloId") String moduloId);
}
