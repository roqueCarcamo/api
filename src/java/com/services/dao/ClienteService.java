package com.services.dao;

import com.services.model.Cliente;
import java.util.List;
import com.services.model.User;
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
    Cliente findById(long id, ConfiguracionDataBase conf) throws Exception;

    /**
     * @descripcion Método para obtener usuario por nombre.
     *
     * @param name
     * @return User user.
     */
    User findByName(String name);

    /**
     * @descripcion Método para validar crear un usuario.
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * @descripcion Método para actualizar un usuario.
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * @descripcion Método para eliminar un usuario por id.
     *
     * @param id
     */
    void deleteUserById(long id);

    /**
     * @descripcion Método para listar todos los usuarios.
     *
     * @return List<User> users
     */
    List<User> findAllUsers();

    /**
     * @descripcion Método para eliminar todos los usuarios.
     *
     */
    void deleteAllUsers();

    /**
     * @descripcion Método para validar si existe un usuario.
     *
     * @param user
     * @return boolean valido.
     */
    public boolean isUserExist(User user);

}
