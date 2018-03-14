package com.services.dao.impl;

import com.services.dao.UserService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.services.model.User;

/**
 * @descripcion: Ejemplo de clase que implementa los métodos de tipo CRUD. Autor:
 * @autor: (Rodolfo Cárcamo) 
 * @fechaCreacion: 12-04-2017
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final AtomicLong counter = new AtomicLong(); //Generador de id

    private static List<User> users; //Lista de usuarios.

    static {
        users = populateDummyUsers();
    }

    /**
     * @descripcion Método para buscar usuario por id.
     *
     * @param long id
     * @return User user.
     */
    @Override
    public User findById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /**
     * @descripcion Método para obtener usuario por nombre.
     *
     * @param String name
     * @return User user.
     */
    @Override
    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @descripcion Método para validar crear un usuario.
     *
     * @param User user
     */
    @Override
    public void saveUser(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
    }

    /**
     * @descripcion Método para actualizar un usuario.
     *
     * @param User user
     */
    @Override
    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    /**
     * @descripcion Método para eliminar un usuario por id.
     *
     * @param long id
     */
    @Override
    public void deleteUserById(long id) {
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }

    /**
     * @descripcion Método para listar todos los usuarios.
     *
     * @return List<User> users
     */
    @Override
    public List<User> findAllUsers() {
        return users;
    }

    /**
     * @descripcion Método para eliminar todos los usuarios.
     *
     */
    @Override
    public void deleteAllUsers() {
        users.clear();
    }

    /**
     * @descripcion Método para validar si existe un usuario.
     *
     * @param User user
     * @return boolean valido.
     */
    @Override
    public boolean isUserExist(User user) {
        return findByName(user.getName()) != null;
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
