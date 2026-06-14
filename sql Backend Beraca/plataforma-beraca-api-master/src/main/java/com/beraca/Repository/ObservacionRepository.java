package com.beraca.Repository;

import com.beraca.model.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beraca.model.Observacion;

@Repository
public interface ObservacionRepository extends JpaRepository<Observacion,Long>{
}
