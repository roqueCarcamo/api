package com.services.configuracion;

import javax.servlet.Filter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @descripcion: Clase que permite inicializar las configuraciones del springframework.
* @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 12-04-2017
*/
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    /**
     * Método para inicializar las configuraciones del springframework..
     *
     * @return Class<?>[] Ceconfiguration.class
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { Ceconfiguration.class };
    }
  
    /**
     * Método del servlet.
     *
     * @return Class<?>[] getServletConfigClasses()
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
 
    /**
     * Método para configurar las urls.
     *
     * @return String[] servletMappings.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    /**
     * Método para iniciar la filtración de las peticiones.
     *
     * @return Filter[] singleton.
     */
    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter()};
        return singleton;
    }
 
}