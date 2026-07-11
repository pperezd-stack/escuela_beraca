package com.beraca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiante_modulo")
public class Estudiante_Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Usuario estudiante;

    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    // ==========================
    // GETTER Y SETTER DEL ID
    // ==========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // ==========================
    // GETTER Y SETTER DEL ESTUDIANTE
    // ==========================

    public Usuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Usuario estudiante) {
        this.estudiante = estudiante;
    }

    // ==========================
    // GETTER Y SETTER DEL MÓDULO
    // ==========================

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

}