package com.beraca.Repository;

import com.beraca.model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    // Método que el UsuarioService necesita para el login del estudiante:
    @Query(value = "SELECT m.* FROM modulos m INNER JOIN estudiante_modulo em ON m.id = em.modulo_id WHERE em.estudiante_id = :estudianteId", nativeQuery = true)
    List<Modulo> findModulosByEstudianteId(@Param("estudianteId") Long estudianteId);
}
