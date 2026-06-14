package com.beraca.Repository;

import com.beraca.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beraca.model.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion,Long>{
}