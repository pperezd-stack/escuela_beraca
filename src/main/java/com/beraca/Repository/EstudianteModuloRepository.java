package com.beraca.Repository;

import com.beraca.model.Estudiante_Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteModuloRepository extends JpaRepository<Estudiante_Modulo, Long> {

    List<Estudiante_Modulo> findByModulo_Id(Long moduloId);

    Optional<Estudiante_Modulo> findByEstudiante_Id(Long estudianteId);

}

