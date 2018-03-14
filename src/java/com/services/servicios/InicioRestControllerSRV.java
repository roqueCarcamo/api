
package com.services.servicios;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @descripcion: Clase de tipo servicio para servicio inicial de la aplicación.
 * @autor: (Fabio Suárez) 
 * @fechaCreacion: 10-10-2017
 */
@RestController
public class InicioRestControllerSRV implements Serializable {
    
    /**
     * Servicio inicial de aplicación.
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "inicio", method = RequestMethod.GET)
    public ResponseEntity estadoInicio() {
        return new ResponseEntity("Servicios", HttpStatus.OK);
    }   
}
