package com.services.dao;

import com.services.model.Cliente;
import java.util.List;
import com.services.model.dataBase.ConfiguracionDataBase;

/**
 * @descripcion: Clase ejemplo que permite declarar métodos de tipo CRUD.
 * @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 12-04-2017
*/
public interface ClienteService {

    /**
     * @throws java.lang.Exception
     * @descripcion Método para buscar usuario por id.
     *
     * @param id
     * @param conf
     * @return User user.
     */
    Cliente findById(long id) throws Exception;

    /**
     * @descripcion Método para obtener usuario por nombre.
     *
     * @param name
     * @return User user.
     */
    Cliente findByName(String name);

    /**
     * @descripcion Método para validar crear un cliente.
     *
     * @param cliente
     */
    void saveCliente(Cliente cliente) throws Exception;

    /**
     * @descripcion Método para actualizar un cliente.
     *
     * @param cliente
     */
    void updateCliente(Cliente cliente);

    /**
     * @descripcion Método para eliminar un cliente por id.
     *
     * @param id
     */
    void deleteClienteById(long id);

    /**
     * @descripcion Método para listar todos los cliente.
     *
     * @return List<Cliente> clientes
     */
    List<Cliente> findAllClientes() throws Exception;

    /**
     * @descripcion Método para eliminar todos los clientes.
     *
     */
    void deleteAllClientes();

    /**
     * @descripcion Método para validar si existe un cliente.
     *
     * @param user
     * @return boolean valido.
     */
    public boolean isClienteExist(Cliente cliente);

}
