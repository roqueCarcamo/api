package com.services.dao;

import com.services.model.Cliente;
import java.util.List;

/**
 * @descripcion: Clase ejemplo que permite declarar métodos de tipo CRUD.
 * @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 15-03-2018
*/
public interface ClienteService {

    /**
     * @throws java.lang.Exception
     * @descripcion Método para buscar cliente por id.
     *
     * @param id
     * @return Cliente cliente.
     */
    Cliente findById(long id) throws Exception;

    /**
     * @descripcion Método para obtener cliente por nombre.
     *
     * @param name
     * @return Cliente cliente.
     */
    Cliente findByName(String name) throws Exception;

    /**
     * @throws java.lang.Exception
     * @descripcion Método para validar crear un cliente.
     *
     * @param cliente
     */
    void saveCliente(Cliente cliente) throws Exception;

    /**
     * @throws java.lang.Exception
     * @descripcion Método para actualizar un cliente.
     *
     * @param cliente
     */
    void updateCliente(Cliente cliente) throws Exception;

    /**
     * @throws java.lang.Exception
     * @descripcion Método para eliminar un cliente por id.
     *
     * @param id
     */
    void deleteClienteById(long id) throws Exception;

    /**
     * @throws java.lang.Exception
     * @descripcion Método para listar todos los cliente.
     *
     * @return List<Cliente> clientes
     */
    List<Cliente> findAllClientes() throws Exception;

    /**
     * @throws java.lang.Exception
     * @descripcion Método para eliminar todos los clientes.
     *
     */
    void deleteAllClientes() throws Exception;

    /**
     * @param cliente
     * @descripcion Método para validar si existe un cliente.
     * @return boolean valido.
     */
    public boolean isClienteExist(Cliente cliente) throws Exception;

}
