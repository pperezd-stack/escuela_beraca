package com.beraca.dto;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String rol;
    private Long moduloId;

    public UsuarioDTO() {
    }

    // Constructor original (sin módulo) - se mantiene por si se usa en otro lugar
    public UsuarioDTO(Long id, String nombre, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }

    // 🟢 Nuevo constructor - incluye el módulo asignado (para profesores)
    public UsuarioDTO(Long id, String nombre, String rol, Long moduloId) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.moduloId = moduloId;
    }

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getModuloId() {
        return moduloId;
    }

    public void setModuloId(Long moduloId) {
        this.moduloId = moduloId;
    }

}
