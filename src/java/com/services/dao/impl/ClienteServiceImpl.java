package com.services.dao.impl;

import com.services.dao.ClienteService;
import com.services.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.services.model.dataBase.ConfiguracionDataBase;
import com.services.servicios.reader.FileReader;
import com.services.servicios.util.SqlUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @descripcion: Ejemplo de clase que implementa los métodos de tipo CRUD.
 * @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 15-05-2018
 */
@Service("clienteService")
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private static List<Cliente> clientes; //Lista de clientes.


    /**
     * @throws java.lang.Exception
     * @descripcion Método para buscar usuario por id.
     *
     * @param id
     * @return user.
     */
    @Override
    public Cliente findById(long id) throws Exception{
        ConfiguracionDataBase conf = FileReader.getConfigurationFromUserName("postgres");
        Connection conexion = SqlUtil.obtenerConexion(conf);
        Statement st = conexion.createStatement();
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM public.\"TCLIENTE\" WHERE \"PK_TCLIENTE\" = ").append(id);
        ResultSet rs = st.executeQuery(str.toString());
        Cliente cliente = new Cliente();
        while (rs.next()) {
            cliente = new Cliente(id, rs.getString(1), rs.getString(2));   
        }
        return cliente;
    }

    /**
     * @descripcion Método para obtener cliente por nombre.
     *
     * @param nombre
     * @return User user.
     */
    @Override
    public Cliente findByName(String nombre) {
        for (Cliente cli : clientes) {
            if (cli.getNombres().equalsIgnoreCase(nombre)) {
                return cli;
            }
        }
        return null;
    }

    /**
     * @throws java.lang.Exception
     * @descripcion Método para validar crear un cliente.
     *
     * @param cliente
     */
    @Override
    public void saveCliente(Cliente cliente) throws Exception {
        ConfiguracionDataBase conf = FileReader.getConfigurationFromUserName("postgres"); 
        Connection conexion = SqlUtil.obtenerConexion(conf);      
        StringBuilder str = new StringBuilder();
        str.append("INSERT INTO public.\"TCLIENTE\"( ");
        str.append(" \"NOMBRES\", \"APELLIDOS\") ");
        str.append(" VALUES (?, ?) ");
        PreparedStatement preparedStatement = conexion.prepareStatement(str.toString());;
        preparedStatement.setString(1, cliente.getNombres());
        preparedStatement.setString(2, cliente.getApellidos());
        preparedStatement.executeUpdate();  
    }

    /**
     * @descripcion Método para actualizar un cliente.
     *
     * @param cliente
     */
    @Override
    public void updateCliente(Cliente cliente) {
        int index = clientes.indexOf(cliente);
        clientes.set(index, cliente);
    }

    /**
     * @throws java.lang.Exception
     * @descripcion Método para eliminar un cliente por id.
     *
     * @param id
     */
    @Override
    public void deleteClienteById(long id) throws Exception {
        ConfiguracionDataBase conf = FileReader.getConfigurationFromUserName("postgres");
        Connection conexion = SqlUtil.obtenerConexion(conf);      
        StringBuilder str = new StringBuilder();
        str.append("DELETE FROM public.\"TCLIENTE\" WHERE \"PK_TCLIENTE\" = ?");
        PreparedStatement preparedStatement = conexion.prepareStatement(str.toString());
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();  
    }

    /**
     * @throws java.lang.Exception
     * @descripcion Método para listar todos los clientes.
     *
     * @return List<Cliente> users
     */
    @Override
    public List<Cliente> findAllClientes() throws Exception {
        ConfiguracionDataBase conf = FileReader.getConfigurationFromUserName("postgres");
        Connection conexion = SqlUtil.obtenerConexion(conf);
        Statement st = conexion.createStatement();
        StringBuilder str = new StringBuilder();
        str.append("SELECT * FROM public.\"TCLIENTE\" ");
        ResultSet rs = st.executeQuery(str.toString());
        Cliente cliente = new Cliente();
        List<Cliente> listClientes = new ArrayList<>();
        while (rs.next()) {
            cliente = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3));
            System.out.println(rs.getString(1));
            listClientes.add(cliente);
        }
        return listClientes;
    }

    /**
     * @descripcion Método para eliminar todos los clientes.
     *
     */
    @Override
    public void deleteAllClientes() throws Exception {
        ConfiguracionDataBase conf = FileReader.getConfigurationFromUserName("postgres");
        Connection conexion = SqlUtil.obtenerConexion(conf);      
        StringBuilder str = new StringBuilder();
        str.append("DELETE FROM public.\"TCLIENTE\" ");
        PreparedStatement preparedStatement = conexion.prepareStatement(str.toString());
        preparedStatement.executeUpdate();  
    }

    /**
     * @descripcion Método para validar si existe un cliente.
     *
     * @param cliente
     * @return boolean valido.
     */
    @Override
    public boolean isClienteExist(Cliente cliente) {
        return findByName(cliente.getNombres()) != null;
    }

}
