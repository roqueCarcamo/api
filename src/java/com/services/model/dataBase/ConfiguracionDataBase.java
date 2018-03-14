package com.services.model.dataBase;

import java.io.Serializable;

/**
 * @descripcion: Clase para la obtener los valores de configuración de la base de datos.
* @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 14-03-2018
*/
public class ConfiguracionDataBase implements Serializable {

    private String nombreUsuario; //Nombre de usuario de la base de dato.
    private String password; //Password de la base de dato.
    private String ip; //Ip de la base de dato.
    private String database; //Nombre de la base de dato.
    private String port; //Puerto de la base de dato.

    /**
     * Método constructor de clase.
     *
     */
    public ConfiguracionDataBase() {
    }

    /**
     * Método constructor de clase 2.
     *
     * @param nombreUsuario
     * @param password
     * @param ip
     * @param database
     * @param port
     */
    public ConfiguracionDataBase(String nombreUsuario, String password, String ip, String database, String port) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.ip = ip;
        this.database = database;
        this.port = port;
    }

    /**
     * Método para obtener valor del nombre de usuario.
     *
     * @return String nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Método para cambiar valor del nombre de usuario.
     *
     * @param nombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Método para obtener valor del password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método para cambiar valor del password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método para obtener valor de ip.
     *
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Método para cambiar valor de ip.
     *
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Método para obtener el valor del nombre de la base de dato.
     *
     * @return database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Método para cambiar el valor del nombre de la base de dato.
     *
     * @param database
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * Método para obtener el valor del purto de la base de dato.
     *
     * @return String port
     */
    public String getPort() {
        return port;
    }

    /**
     * Método para cambia el valor del puerto de la base de dato.
     *
     * @param port
     */
    public void setPort(String port) {
        this.port = port;
    }

}
