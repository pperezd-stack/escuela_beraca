package com.beraca.Repository;

import com.beraca.model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    // Método para buscar módulos del estudiante (vía tabla intermedia)
    @Query(value = "SELECT m.* FROM modulos m INNER JOIN estudiante_modulo em ON m.id = em.modulo_id WHERE em.estudiante_id = :estudianteId", nativeQuery = true)
    List<Modulo> findModulosByEstudianteId(@Param("estudianteId") Long estudianteId);

    // 🟢 NUEVO: Método para buscar los módulos impartidos por un profesor
    @Query(value = "SELECT * FROM modulos WHERE profesor_id = :profesorId", nativeQuery = true)
    List<Modulo> findByProfesorId(@Param("profesorId") Long profesorId);
}
