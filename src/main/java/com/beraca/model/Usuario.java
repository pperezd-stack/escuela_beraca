package com.beraca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String correo;

    private String password;

    private String rol;

    // ==========================
    // MÓDULOS DONDE ES PROFESOR
    // ==========================
    @OneToMany(mappedBy = "profesor")
    @JsonIgnore
    private List<Modulo> modulos;

    // ==========================
    // MÓDULOS DONDE ES ESTUDIANTE
    // ==========================
    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Estudiante_Modulo> matriculas;

    // ==========================
    // GETTERS Y SETTERS
    // ==========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public List<Estudiante_Modulo> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Estudiante_Modulo> matriculas) {
        this.matriculas = matriculas;
    }
}