package com.services.model;

import java.io.Serializable;

/**
 *
 * @author RODOLFO
 */
public class Cliente implements Serializable {

    private Long id;
    private String nombre;
    private String apellidos;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
