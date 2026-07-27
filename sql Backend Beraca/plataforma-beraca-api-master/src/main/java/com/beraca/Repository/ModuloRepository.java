package com.beraca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.beraca.model.Modulo;

import java.util.Optional;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    // 🟢 Nuevo método: busca el módulo que tiene asignado a un profesor específico
    Optional<Modulo> findByProfesorId(Long profesorId);

}
