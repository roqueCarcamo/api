package com.services.model.dataBase;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @descripcion: Clase que permite obtener la conexión a la base de datos.
 * @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 14-03-2018
 */
public class Database implements Serializable {

    public String driver, url, ip, bd, usr, pass, port; //Driver ,url, ip, base de dato, usuario, pasword y puerto de la base de dato.
    public Connection conexion; //Conexión a la base de dato.

    /**
     * Método constructor de la clase.
     *
     * @param ip
     * @param bd
     * @param usr
     * @param pass
     * @param port
     */
    public Database(String ip, String bd, String usr, String pass, String port) {
        driver = "org.postgresql.Driver";
        this.bd = bd;
        this.usr = usr;
        this.pass = pass;
        url = "jdbc:postgresql://" + ip + ":" + port + "/" + bd;
        try {
            Class.forName(driver).newInstance();
            conexion = DriverManager.getConnection(url, usr, pass);
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("Select * from public.orders");
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }

            Logger.getLogger(Database.class.getName()).log(Level.INFO, "Conexión a Base de Datos " + bd + " Ok ", "");
        } catch (Exception exc) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Error al tratar de abrir la base de Datos " + ip + " BD " + bd + " Usuario " + usr + " Password " + pass + " Puerto " + port, exc);
        }
    }

    /**
     * Método para obtener la conexion a la base de dato.
     *
     * @return Connection conexion
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Método para cerrar la conexion a la base de dato.
     *
     * @return Connection conexion
     * @throws SQLException
     */
    public Connection CerrarConexion() throws SQLException {
        conexion.close();
        conexion = null;
        return conexion;
    }

}
