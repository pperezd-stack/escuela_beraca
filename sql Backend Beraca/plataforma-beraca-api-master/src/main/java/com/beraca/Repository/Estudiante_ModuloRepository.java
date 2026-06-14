package com.beraca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beraca.model.Estudiante_Modulo;

@Repository
public interface Estudiante_ModuloRepository extends JpaRepository<Estudiante_Modulo,Long>{
}
