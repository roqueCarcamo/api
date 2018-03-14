package com.services.model;

import java.io.Serializable;

/**
 * @descripcion: Clase de ejemplo de entidad. 
* @autor: (Rodolfo Cárcamo) 
 * @fechaCreacion: 12-04-2017
 */
public class User implements Serializable {

    private long id; //Identificador
    private String name; //Nombre
    private int age; //Edad
    private double salary; //Salario

    /**
     * Método constructor de la clase.
     *
     */
    public User() {
        id = 0;
    }

    /**
     * Método constructor de la clase 2.
     *
     * @param long id
     * @param String name
     * @param int age
     * @param double salary
     */
    public User(long id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    /**
     * Método para obtener id.
     *
     * @return long id
     */
    public long getId() {
        return id;
    }

    /**
     * Método para cambiar id.
     *
     * @param long id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Método para obtener name.
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Método para cambiar name.
     *
     * @param String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método para obtener age.
     *
     * @return int age
     */
    public int getAge() {
        return age;
    }

    /**
     * Método para cambiar age.
     *
     * @param int age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Método para obtener salary.
     *
     * @return double salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Método para cambiar salary.
     *
     * @param double salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Método para generar hashcode.
     *
     * @return int result
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    /**
     * Método para camparar con otro objeto.
     *
     * @return boolean valorBoolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * Método para obtener string de todos los atributos.
     *
     * @return String user
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age
                + ", salary=" + salary + "]";
    }

}
