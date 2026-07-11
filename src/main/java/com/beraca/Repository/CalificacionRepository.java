package com.beraca.Repository;

import com.beraca.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    List<Calificacion> findByEstudiante_Id(Long estudianteId);

    List<Calificacion> findByModulo_Id(Long moduloId);

    Optional<Calificacion> findByEstudiante_IdAndModulo_Id(
            Long estudianteId,
            Long moduloId
    );

}