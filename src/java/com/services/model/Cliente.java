package com.services.model;

import java.io.Serializable;

/**
 *
 * @author RODOLFO
 */
public class Cliente implements Serializable {

    private Long id;
    private String nombres;
    private String apellidos;

    public Cliente() {
    }

    public Cliente(Long id, String nombres, String apellidos) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
