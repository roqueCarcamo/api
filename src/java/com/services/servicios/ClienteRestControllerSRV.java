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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @descripcion: Clase ejemplo de servicios Cliente.
 * @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 12-03-2018
 */
@RestController
public class ClienteRestControllerSRV implements Serializable {

    @Autowired
    ClienteService clienteService;  //Servicio que hará todo el trabajo de recuperación / manipulación de datos

    /**
     * @descripcion Método para recuperar todos los cliente.
     *
     * @return users
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-03-2018
     */
    @RequestMapping(value = "clientes", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = clienteService.findAllClientes();
        } catch (Exception ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
        if (clientes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    /**
     * @descripcion Método para recuperar un solo cliente.
     *
     * @param id
     * @return user
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-03-2018
     */
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> getCliente(@PathVariable("id") long id) {
        Cliente cliente;
        try {
            cliente = clienteService.findById(id);
            if (cliente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    /**
     * @descripcion Método para crear un cliente.
     *
     * @param cliente, ucBuilder
     * @param ucBuilder
     * @return void;
     */
    @RequestMapping(value = "/cliente/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCliente(@RequestBody Cliente cliente, UriComponentsBuilder ucBuilder) {
        try {
            clienteService.saveCliente(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * @descripcion método para actualizar un cliente.
     *
     * @param id
     * @param cliente
     * @return users;
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-03-2018
     */
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteCurrent;
            try {
                clienteCurrent = clienteService.findById(id);
                if (clienteCurrent == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception ex) {
                Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>(HttpStatus.valueOf(500));
            }
            if (clienteCurrent == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            clienteCurrent.setNombres(cliente.getNombres());
            clienteCurrent.setApellidos(cliente.getApellidos());
            clienteService.updateCliente(clienteCurrent);
            return new ResponseEntity<>(clienteCurrent, HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    /**
     * @descripcion Método para eliminar un cliente.
     *
     * @param id
     * @return users;
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-03-2018
     */
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteCliente(@PathVariable("id") long id) {
        try {
            Cliente clienteCurrent = new Cliente();
            try {
                clienteCurrent = clienteService.findById(id);
                if (clienteCurrent == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception ex) {
                Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (clienteCurrent == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            clienteService.deleteClienteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    /**
     * @descripcion Método para eliminar todos los clientes.
     *
     * @return users;
     * @autor: (Rodolfo Cárcamo)
     * @fechaCreacion: 12-03-2018
     */
    @RequestMapping(value = "/cliente/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllClientes() {
        try {
            clienteService.deleteAllClientes();
        } catch (Exception ex) {
            Logger.getLogger(ClienteRestControllerSRV.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
