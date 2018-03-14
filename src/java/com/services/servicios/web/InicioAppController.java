package com.services.servicios.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @descripcion: Clase de tipo servicio que inicia principalmente.
* @autor: (Fabio Suárez) 
 * @fechaCreacion: 10-105-2017
 */
@Controller
public class InicioAppController {

    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Agregar el servicio inicial de aplicación.
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Inicio de apliación ok");
        return new ModelAndView("inicio");
    }
}
