package com.beraca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "calificaciones")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================
    // RELACIÓN CON ESTUDIANTE
    // ==========================

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Usuario estudiante;

    // ==========================
    // RELACIÓN CON MÓDULO
    // ==========================

    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    // ==========================
    // NOTAS
    // ==========================

    private String corte1;
    private String corte2;
    private String corte3;

    @Column(name = "nota_final")
    private String notaFinal;

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
    // CORTES
    // ==========================

    public String getCorte1() {
        return corte1;
    }

    public void setCorte1(String corte1) {
        this.corte1 = corte1;
    }

    public String getCorte2() {
        return corte2;
    }

    public void setCorte2(String corte2) {
        this.corte2 = corte2;
    }

    public String getCorte3() {
        return corte3;
    }

    public void setCorte3(String corte3) {
        this.corte3 = corte3;
    }

    // ==========================
    // NOTA FINAL
    // ==========================

    public String getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(String notaFinal) {
        this.notaFinal = notaFinal;
    }

}