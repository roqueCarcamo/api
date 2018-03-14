package com.services.servicios.listener;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @descripcion: Clase que permite inicializar la ruta de la aplicación.
* @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 12-04-2017
*/
public class AplicacionServletListener implements ServletContextListener {

    /**
     * Método para iniciliazar la ruta del contexto de la aplicación.
     *
     * @param sce.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ContextoAplicacion.getInstance().setRutaContextoAplicacion(sce.getServletContext().getRealPath(File.separator));
    }

    /**
     * Método para destruir el contexto de la aplicación.
     *
     * @param sce.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
