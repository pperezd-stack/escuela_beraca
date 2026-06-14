package com.beraca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "calificaciones")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estudiante_id")
    private Long estudiante_id;

    @Column(name = "modulo_id")
    private Long modulo_id;

    private String corte1;
    private String corte2;
    private String corte3;

    @Column(name = "nota_final")
    private String notaFinal;

    // --- GETTERS Y SETTERS ---

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

    public String getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(String notaFinal) {
        this.notaFinal = notaFinal;
    }
}