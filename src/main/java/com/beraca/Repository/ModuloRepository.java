package com.beraca.Repository;

import com.beraca.model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    // Módulo asignado a un profesor
    Optional<Modulo> findByProfesor_Id(Long profesorId);

}