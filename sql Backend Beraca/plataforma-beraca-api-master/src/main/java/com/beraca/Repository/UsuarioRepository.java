package com.beraca.Repository;

import com.beraca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consulta nativa directa a MySQL para evitar filtros problemáticos en Java
    @Query(value = "SELECT * FROM usuarios WHERE UPPER(TRIM(rol)) = 'ESTUDIANTE'", nativeQuery = true)
    List<Usuario> buscarSoloEstudiantes();
}