package com.appsalon.backend.api.DTO;

public class LoginResponse {

    private Long id;
    private String nombre;
    private String email;
    private Long rol_id;
    private String rol_nombre;

    public LoginResponse(Long id, String nombre, String email, Long rol_id, String rol_nombre) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol_id = rol_id;
        this.rol_nombre = rol_nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Long getRol_id() {
        return rol_id;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }
}
