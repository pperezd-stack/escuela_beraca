package com.beraca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "observaciones")
public class Observacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long estudiante_id;
    private Long modulo_id;

    private String comentarioCorte1;
    private String comentarioCorte2;
    private String comentarioCorte3;
    private String fecha;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEstudiante_id() { return estudiante_id; }
    public void setEstudiante_id(Long estudiante_id) { this.estudiante_id = estudiante_id; }

    public Long getModulo_id() { return modulo_id; }
    public void setModulo_id(Long modulo_id) { this.modulo_id = modulo_id; }

    public String getComentarioCorte1() { return comentarioCorte1; }
    public void setComentarioCorte1(String comentarioCorte1) { this.comentarioCorte1 = comentarioCorte1; }

    public String getComentarioCorte2() { return comentarioCorte2; }
    public void setComentarioCorte2(String comentarioCorte2) { this.comentarioCorte2 = comentarioCorte2; }

    public String getComentarioCorte3() { return comentarioCorte3; }
    public void setComentarioCorte3(String comentarioCorte3) { this.comentarioCorte3 = comentarioCorte3; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}