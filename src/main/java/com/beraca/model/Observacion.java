package com.beraca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "observaciones")
public class Observacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Usuario estudiante;

    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    private String comentarioCorte1;
    private String comentarioCorte2;
    private String comentarioCorte3;
    private String fecha;

    // ==========================
    // ID
    // ==========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // ==========================
    // ESTUDIANTE
    // ==========================

    public Usuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Usuario estudiante) {
        this.estudiante = estudiante;
    }

    // ==========================
    // MÓDULO
    // ==========================

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    // ==========================
    // COMENTARIOS
    // ==========================

    public String getComentarioCorte1() {
        return comentarioCorte1;
    }

    public void setComentarioCorte1(String comentarioCorte1) {
        this.comentarioCorte1 = comentarioCorte1;
    }

    public String getComentarioCorte2() {
        return comentarioCorte2;
    }

    public void setComentarioCorte2(String comentarioCorte2) {
        this.comentarioCorte2 = comentarioCorte2;
    }

    public String getComentarioCorte3() {
        return comentarioCorte3;
    }

    public void setComentarioCorte3(String comentarioCorte3) {
        this.comentarioCorte3 = comentarioCorte3;
    }

    // ==========================
    // FECHA
    // ==========================

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}