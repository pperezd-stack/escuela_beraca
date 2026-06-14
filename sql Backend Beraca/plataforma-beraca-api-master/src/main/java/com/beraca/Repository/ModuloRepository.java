package com.beraca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beraca.model.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo,Long>{
}
