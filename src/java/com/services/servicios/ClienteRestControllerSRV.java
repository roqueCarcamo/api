package com.services.servicios;

import com.services.dao.ClienteService;
import com.services.model.Cliente;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.services.model.User;
import com.services.model.dataBase.ConfiguracionDataBase;
import com.services.servicios.reader.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.xml.sax.SAXException;

/**
 * @descripcion: Clase ejemplo de servicios.
 * @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 12-04-2017
 */
@RestController
public class ClienteRestControllerSRV implements Serializable {

    @Autowired
    ClienteService clienteService;  //Servicio que hará todo el trabajo de recuperación / manipulación de datos

    /**
     * @descripcion Método para recuperar todos los usuarios.
     *
     * @return users
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-04-2017
     */
    @RequestMapping(value = "clientes", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {

        ConfiguracionDataBase conf = null;
        try {
            conf = FileReader.getConfigurationFromUserName("postgres");

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<User> users = clienteService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * @descripcion Método para recuperar un solo usuario.
     *
     * @param id
     * @return user
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-04-2017
     */
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> getUser(@PathVariable("id") long id) {
        ConfiguracionDataBase conf = null;
        Cliente cliente = new Cliente();
        try {
            conf = FileReader.getConfigurationFromUserName("postgres");

            cliente = clienteService.findById(id, conf);
            if (cliente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    /**
     * @descripcion Método para crear un usuario.
     *
     * @param user, ucBuilder
     * @param ucBuilder
     * @return void;
     */
    @RequestMapping(value = "/cliente/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        if (clienteService.isUserExist(user)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        clienteService.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cliente/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * @descripcion Método para actualizar un usuario.
     *
     * @param id, user
     * @param user
     * @return users;
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-04-2017
     */
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Cliente> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        ConfiguracionDataBase conf = null;
        Cliente clienteCurrent = new Cliente();
        try {
            conf = FileReader.getConfigurationFromUserName("postgres");

            clienteCurrent = clienteService.findById(id, conf);
            if (clienteCurrent == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clienteCurrent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //currentUser.setName(user.getName());
        //currentUser.setAge(user.getAge());
        //currentUser.setSalary(user.getSalary());
        //clienteService.updateUser(clienteCurrent);
        return new ResponseEntity<>(clienteCurrent, HttpStatus.OK);
    }

    /**
     * @descripcion Método para eliminar un usuario.
     *
     * @param id
     * @return users;
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-04-2017
     */
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        ConfiguracionDataBase conf = null;
        Cliente clienteCurrent = new Cliente();
        try {
            conf = FileReader.getConfigurationFromUserName("postgres");

            clienteCurrent = clienteService.findById(id, conf);
            if (clienteCurrent == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clienteCurrent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clienteService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * @descripcion Método para eliminar todos los usuarios.
     *
     * @return users;
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-04-2017
     */
    @RequestMapping(value = "/cliente/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        clienteService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
