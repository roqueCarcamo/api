package com.services.dao.impl;

import com.services.dao.ClienteService;
import com.services.model.Cliente;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.services.model.User;
import com.services.model.dataBase.ConfiguracionDataBase;
import com.services.servicios.ClienteRestControllerSRV;
import com.services.servicios.reader.FileReader;
import com.services.servicios.util.SqlUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * @descripcion: Ejemplo de clase que implementa los métodos de tipo CRUD.
 * Autor:
 * @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 12-04-2017
 */
@Service("clienteService")
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private static final AtomicLong counter = new AtomicLong(); //Generador de id

    private static List<Cliente> clientes; //Lista de clientes.


    /**
     * @throws java.lang.Exception
     * @descripcion Método para buscar usuario por id.
     *
     * @param id
     * @param conf
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
            System.out.println(rs.getString(1));   
        }
        return cliente;
    }

    /**
     * @descripcion Método para obtener cliente por nombre.
     *
     * @param String name
     * @return User user.
     */
    @Override
    public Cliente findByName(String name) {
        for (Cliente cli : clientes) {
            if (cli.getNombre().equalsIgnoreCase(name)) {
                return cli;
            }
        }
        return null;
    }

    /**
     * @descripcion Método para validar crear un cliente.
     *
     * @param Cliente cliente
     */
    @Override
    public void saveCliente(Cliente cliente) throws Exception {
        ConfiguracionDataBase conf = FileReader.getConfigurationFromUserName("postgres");
        cliente.setId(counter.incrementAndGet());  
        Connection conexion = SqlUtil.obtenerConexion(conf);
        Statement st = conexion.createStatement();
        StringBuilder str = new StringBuilder();
        str.append("INSERT INTO public.\"TCLIENTE\"( ");
        str.append(" \"PK_TCLIENTE\", \"NOMBRES\", \"APELLIDOS\") ");
        str.append(" VALUES (?, ?, ?) ");
        ResultSet rs = st.executeQuery(str.toString());
    }

    /**
     * @descripcion Método para actualizar un cliente.
     *
     * @param Cliente cliente
     */
    @Override
    public void updateCliente(Cliente cliente) {
        int index = clientes.indexOf(cliente);
        clientes.set(index, cliente);
    }

    /**
     * @descripcion Método para eliminar un cliente por id.
     *
     * @param long id
     */
    @Override
    public void deleteClienteById(long id) {
        for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
            Cliente cliente = iterator.next();
            if (cliente.getId() == id) {
                iterator.remove();
            }
        }
    }

    /**
     * @param conf
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
    public void deleteAllClientes() {
        clientes.clear();
    }

    /**
     * @descripcion Método para validar si existe un cliente.
     *
     * @param User user
     * @return boolean valido.
     */
    @Override
    public boolean isClienteExist(Cliente cliente) {
        return findByName(cliente.getNombre()) != null;
    }

    private static List<User> populateDummyUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(counter.incrementAndGet(), "Sam", 30, 70000));
        users.add(new User(counter.incrementAndGet(), "Tom", 40, 50000));
        users.add(new User(counter.incrementAndGet(), "Jerome", 45, 30000));
        users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000));
        return users;
    }

}
