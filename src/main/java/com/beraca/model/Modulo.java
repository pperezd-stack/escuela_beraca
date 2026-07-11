package com.beraca.model;


    import jakarta.persistence.*;
    @Entity
    @Table(name = "modulos")
    public class Modulo {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
        private String nombre;
        private String orden;
        // Profesor encargado del módulo
         @ManyToOne
        @JoinColumn(name = "profesor_id")
         private Usuario profesor; public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getOrden() { return orden; }
        public void setOrden(String orden) { this.orden = orden; }
        public Usuario getProfesor() { return profesor; }
        public void setProfesor(Usuario profesor) { this.profesor = profesor; }
    }
