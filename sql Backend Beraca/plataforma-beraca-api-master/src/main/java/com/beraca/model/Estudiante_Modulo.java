package com.beraca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiante_modulo")
public class Estudiante_Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long estudiante_id;
    private Long modulo_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudiante_id() {
        return estudiante_id;
    }

    public void setEstudiante_id(Long estudiante_id) {
        this.estudiante_id = estudiante_id;
    }

    public Long getModulo_id() {
        return modulo_id;
    }

    public void setModulo_id(Long modulo_id) {
        this.modulo_id = modulo_id;
    }
}
