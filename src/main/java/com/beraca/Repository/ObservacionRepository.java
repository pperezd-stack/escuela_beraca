package com.beraca.Repository;

import com.beraca.model.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObservacionRepository extends JpaRepository<Observacion, Long> {

    List<Observacion> findByEstudiante_Id(Long estudianteId);

    List<Observacion> findByModulo_Id(Long moduloId);

    Optional<Observacion> findByEstudiante_IdAndModulo_Id(
            Long estudianteId,
            Long moduloId
    );

}