package com.beraca.dto;

public class ProfesorRegistroDTO {

    private String nombre;
    private String email;
    private String password;
    private String rol; // ej: "PROFESOR"
    private Long moduloId; // ID del módulo seleccionado en React

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Long getModuloId() { return moduloId; }
    public void setModuloId(Long moduloId) { this.moduloId = moduloId; }
}
